<template>
  <div class="table" :style="{height:mHeight}">

    <div class="opt_all">
      <div style="  display: inline-block; margin-right:20px">
        <el-button type="primary" size="small" >选中详情</el-button>
      </div>
      <div style="display: inline-block; position: absolute; bottom:1px; ">
        <Input v-model="value1">
             <Select v-model="select" slot="prepend" style="width: 100px ">

                 <Option v-for="(value, key) in columns" :key="key" :value="value.key">{{value.title}}</Option>

             </Select>
         </Input>

      </div>
      <div  class="opt_right">


        <el-button-group style="margin-right:10px;">


          <el-popover
            placement="left-start"
            width="100"
            trigger="click">

            <CheckboxGroup v-model="check" >
                <Checkbox  v-for="(value, key) in columns" :key="key" class="opt_checkbox" :label="value.title"></Checkbox>

            </CheckboxGroup>

            <el-button  size="small" type="primary" icon="el-icon-menu" slot="reference"></el-button>
          </el-popover>


        </el-button-group>

        <el-button-group style="margin-right:10px;">

          <el-tooltip content="导出选中数据" placement="bottom">
              <el-button size="small" type="primary" icon="el-icon-document"></el-button>
          </el-tooltip>

          <el-tooltip content="删除选中数据 " placement="bottom">
              <el-button size="small" type="warning" icon="el-icon-delete"></el-button>
          </el-tooltip>

        </el-button-group>

        <el-button-group>

          <el-tooltip content="下载所有数据 " placement="bottom">
              <el-button size="small" type="success" icon="el-icon-download"></el-button>
          </el-tooltip>

          <el-tooltip content="打印" placement="bottom">
              <el-button size="small" type="success" icon="el-icon-printer"></el-button>
          </el-tooltip>

          <el-tooltip content="删除所有数据 " placement="bottom">
              <el-button size="small" type="danger" icon="el-icon-delete"></el-button>
          </el-tooltip>

        </el-button-group>
      </div>

    </div>

    <el-table
       :data="tableData"
       style="width: 100%"
       border
       height="100%"
       :row-style="rowStyle"
       @sort-change="sortChange"
       :default-sort = "{prop: 'date'}">
       <el-table-column
         type="selection"
         width="55">
       </el-table-column>
       <el-table-column
         fixed
         prop="id"
         sortable
         label="id"
         width="60">
       </el-table-column>
       <el-table-column
        v-for="(value, key) in columns" :key="key"
        v-if="isContain(value.title)"
         :prop="value.key"
         :label="value.title"
         :width="value.height"
         show-overflow-tooltip>
       </el-table-column>

       <el-table-column
         fixed="right"
         prop="action"
         label="操作"
         width="200">
         <template slot-scope="scope">
           <el-button type="text" size="small" @click="tranform1(scope.row)">编辑</el-button>
           <el-button type="text" size="small" disabled v-if="scope.row.service_status == 1">替换</el-button>
           <el-button type="text" size="small" @click="tran(scope.row)" v-else>替换</el-button>
           <el-button type="text" size="small" @click="use(scope.row)">使用</el-button>
           <el-button type="text" size="small" @click="deletedata(scope.row)">删除</el-button>
         </template>
       </el-table-column>
     </el-table>

     <div class="block">

        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 20, 50]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>

      <el-dialog title="替换操作" :visible.sync="dialogFormVisible" @close="close">
        <el-form :model="form">
          <el-form-item label="替换服务" >
            <el-select v-model="form.selectID" placeholder="请选择服务" style="width:80%">
              <el-option v-for="(item, index) in showData" :key="index" :label="item.CSTitle" :value="item.SID"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="conso">取 消</el-button>
          <el-button type="primary" @click="confirm">确 定</el-button>
        </div>
      </el-dialog>


      <el-dialog title="修改操作" :visible.sync="dialogForm1Visible" @close="close">
        <el-form ref="form" :model="form1" label-width="100px">
            <el-form-item >
                <span>说明：新添加项不会直接生效，需要到服务表中，“手动-替换” 为页面展示项</span>
            </el-form-item>
            <el-form-item label="服务标题">
              <el-input v-model="form1.service_title"  placeholder="输入20个字符以内" clearable></el-input>
            </el-form-item>
            <el-form-item label="服务内容">
              <el-input v-model="form1.service_desc"  placeholder="输入20个字符以内" clearable></el-input>
            </el-form-item>
            <el-form-item label="图片网络地址">
              <el-input v-model="form1.file_url" placeholder="输入图片地址" clearable></el-input>
            </el-form-item>
            <el-form-item label="更换图片">
                  <el-upload
                    class="upload-demo"
                    ref="upload"
                    action="/api/business/updateBusinessService"
                    :file-list="fileList"
                    :data=form1
                    :headers="getHeader"
                    :on-success="onSuccess"
                    :on-error="onError"
                    :on-change="addFile"
                    :auto-upload="false">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                  </el-upload>
            </el-form-item>

            <el-form-item >
              <span>说明：如果同时提交 网络地址 和 文件 ，则默认使用上传的 文件 覆盖 网络地址</span>
            </el-form-item>

              <el-button style="margin-left: 10px;" size="small" type="primary" @click="submitUpload">提交到服务器</el-button>
            </el-form-item>
          </el-form>
      </el-dialog>


  </div>
</template>

<script>
import https from '../https.js'
  export default {
    props: {
      mHeight: {
        type: String,
        default: "1000px"
      }
    },
    data () {
        return {
          dialogFormVisible: false,
          dialogForm1Visible: false,
          fileList:[],
          form1: {
            service_id:'',
            service_title: '',
            service_desc: '',
            file_url : ''
          },
          form2: {
            service_id: '',
            is_use: '1'
          },
          form: {
            selectID: ''
          },
          select_id : '',
          showData : [],
          check: ['服务编号', '服务图片', '服务标题', '服务内容', '状态', '日期'],
          pageNum : 1,
          pageSize : 5,
          total: 0,
          vertical: 'apple',
          value1: '',
          select: '',
          columns: [
                  {
                      title: '服务编号',
                      key: 'service_id',
                      height: 250
                  },
                  {
                      title: '服务图片',
                      key: 'service_img',
                      height: 200
                  },
                  {
                      title: '服务标题',
                      key: 'service_title',
                      height: 200
                  },
                  {
                      title: '服务内容',
                      key: 'service_desc',
                      height: 200
                  },
                  {
                      title: '状态',
                      key: 'service_status',
                      height: 200
                  },
                  {
                      title: '日期',
                      key: 'service_create_time',
                      height: 200
                  }
              ],
          tableData: []

        }
    },
    computed:{
      getHeader(){
        var store = require('store')
        return {'Authorization':'bearer '+store.get('token')}
      }
    },
    methods:{
      deletedata(row){
        this.dialogForm2Visible = true
        this.form2= {
          service_id: row.service_id,
          is_use: '0'
        }
        https.fetchPost("/api/business/changeBusinessService",this.form2).then((data) => {
                      if(data.data.status != 20000){
                        this.onError(data.data)
                      }else{
                        this.getData()
                        this.form2= {
                          service_id: '',
                          is_use: ''
                        }
                        this.success()
                      }
                    })
                    .catch((err)=>{
                        this.onError(err)

                    })
      },
      use(row){
          this.dialogForm2Visible = true
          this.form2= {
            service_id: row.service_id,
            is_use: '1'
          }
          https.fetchPost("/api/business/changeBusinessService",this.form2).then((data) => {
                        if(data.data.status != 20000){
                          this.onError(data.data)
                        }else{
                          this.getData()
                          this.form2= {
                            service_id: '',
                            is_use: ''
                          }
                          this.success()
                        }
                      })
                      .catch((err)=>{
                          this.onError(err)

                      })
      },
      isContain(text){
        for( var i in this.check){
          if(text == this.check[i]){
            return true
          }
        }
        return false
      },

      addFile(file,fileList){
        this.fileList = fileList
      },
       submitUpload(){

         if(this.fileList.length == 0){
           https.fetchPost("/api/business/updateBusinessService",this.form1).then((data) => {
                         if(data.data.status != 20000){
                           this.onError(data.data)
                         }else{
                           this.getData()
                           this.dialogForm1Visible=false
                           this.form1={
                               service_id:'',
                               service_title: '',
                               service_desc: '',
                               file_url : ''
                             },
                           this.success()
                         }
                       })
                       .catch((err)=>{
                           this.onError(err)

                       })
         }else{
           console.log("aaa");
            this.$refs.upload.submit();
         }
       },
       success(){
         this.$message({
            duration: 1000,
            message: '操作成功',
            type: 'success'
          });
       },
      onSuccess(response, file, fileList){
        if(response.status != 20000){
          this.onError(response)
        }else{
          this.getData()
          this.dialogForm1Visible=false
          this.form1={
              service_id:'',
              service_title: '',
              service_desc: '',
              file_url : ''
            },
          this.success()
        }
      },
       onError(err, file, fileList){
         if(err.message == 'Request failed with status code 401'){
             this.$router.push({path: '/login'})
             return;
         }
         if( typeof(err.message) != 'undefined'){
           this.$message({
             duration: 1000,
             message: err.message,
             type: 'warning'
           });
         }else{
           this.$message({
             duration: 1000,
             message: '出现错误，请重试或重新登录',
             type: 'warning'
           });
         }

        },
      close(){
        this.dialogFormVisible = false
        this.select_id = ''
        this.form.selectID = ''
      },
      conso(){
        this.dialogFormVisible = false
        this.select_id = ''
        this.form.selectID = ''
      },
      confirm(){
        https.fetchPost("/api/business/updateService",{'old_service_id':this.form.selectID,'tra_service_id':this.select_id}).then((data) => {
                        if(data.data.status != 20000){
                          this.onError(data.data)
                        }else{
                          this.getData()
                          this.success()
                        }
                        //console.log('thenthenthenthen',data.data)

                    })
                    .catch((err)=>{
                        this.onError(err)
                    })
        this.dialogFormVisible = false
        this.select_id = ''
        this.form.selectID = ''
      },
      tran(row){
        this.select_id = row.service_id
        this.dialogFormVisible = true
        https.fetchGet("/api/business/getServiceShow").then((data) => {
                        if(data.data.status != 20000){
                          this.onError(data.data)
                        }else{
                          console.log( data.data.data);
                          this.showData = data.data.data
                        }

                        //console.log('thenthenthenthen',data.data.data)
                    })
                    .catch((err)=>{
                        this.onError(err)
                    })
      },
      tranform1(row){
        this.fileList =[]
        this.dialogForm1Visible = true
        this.form1= {
          service_id :row.service_id,
          service_title: row.service_title,
          service_desc: row.service_desc,
          file_url : row.service_img
        }
      },
      rowStyle({row, rowIndex}) {
         if (row.service_status === 1) {
           return 'background-color: #f0f9eb';
         }
         return '';
       },
      handleSizeChange(size){
        this.pageSize = size
        this.getData()
      },
      handleCurrentChange(page){
        this.pageNum = page
        this.getData()
      },
      sortChange(){
        console.log("aa");
      },
      getData(){
        https.fetchGet("/api/business/getServices",{'pageNum': this.pageNum, 'pageSize': this.pageSize}).then((data) => {
                        if(data.data.status != 20000){
                          this.onError(data.data)
                        }else{
                          this.tableData = data.data.data.list
                          this.total = data.data.data.total
                        }

                    })
                    .catch((err)=>{
                        this.onError(err)
                    })
      }
    },
    created(){
      this.getData()
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.table{
  position: relative;
  height: 200px;
  overflow: hidden;
  padding: 10px 20px 100px 20px;
}
.block{
  margin-top: 10px;

}
.opt_all{
  position: relative;
  margin-bottom: 10px;
  width: 100%;
}
.opt_right{
  display: inline-block;
  position: absolute;
  right: 10px;
}
.opt_checkbox{
  display: block;
  margin-top: 5px;
}

</style>
