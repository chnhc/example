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
       @sort-change="sortChange"
        :row-style="rowStyle"
       :default-sort = "{prop: 'date'}"
        @select ="selectOne">
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
           <el-button type="text" size="small" disabled v-if="scope.row.business_use == 1 && scope.row.index_use == 1 ">替换</el-button>
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

      <el-dialog title="使用/删除操作" :visible.sync="dialogForm2Visible" @close="close">
        <el-form :model="form2">
          <el-form-item label="选择使用/删除页面" >
            <el-select v-model="form2.index" placeholder="请选择页面" style="width:80%"  >
              <el-option  label="主页使用/删除" value="1"></el-option>
                <el-option  label="业务页使用/删除" value="2"></el-option>
            </el-select>

          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="conso">取 消</el-button>
          <el-button type="primary" @click="confirm2">确 定</el-button>
        </div>
      </el-dialog>

      <el-dialog title="替换操作" :visible.sync="dialogFormVisible" @close="close">
        <el-form :model="form">
          <el-form-item label="替换主页案例" >
            <el-select v-model="form.selectIndexID" placeholder="请选择案例" style="width:80%" v-if="select_row.index_use != 1" >
              <el-option v-for="(item, index) in showIndexData" :key="index" :label="item.SCTitle" :value="item.CID"></el-option>
            </el-select>
            <el-select v-model="form.selectIndexID" placeholder="已经存在" style="width:80%" v-else disabled></el-select>
          </el-form-item>
          <el-form-item label="替换业务案例" >
            <el-select v-model="form.selectBusinessID" placeholder="请选择案例" style="width:80%" v-if="select_row.business_use != 1" >
              <el-option v-for="(item, index) in showBusinessData" :key="index" :label="item.SCTitle" :value="item.CID"></el-option>
            </el-select>
            <el-select v-model="form.selectBusinessID" placeholder="已经存在" style="width:80%" v-else disabled></el-select>
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
                    <span>说明：新添加项不会直接生效，需要到案例表中，“手动-替换” 为页面展示项</span>
                </el-form-item>
                <el-form-item label="案例标题">
                  <el-input v-model="form1.case_title" placeholder="输入20个字符以内" clearable></el-input>
                </el-form-item>
                <el-form-item label="案例副标题">
                  <el-input v-model="form1.case_subhead"  placeholder="输入20个字符以内" clearable></el-input>
                </el-form-item>
                <el-form-item label="案例说明">
                  <el-input type="textarea" v-model="form1.case_text"></el-input>
                </el-form-item>
                <el-form-item label="图片网络地址">
                  <el-input v-model="form1.file_url" placeholder="输入图片地址" clearable></el-input>
                </el-form-item>
                <el-form-item label="更换图片">
                      <el-upload
                        class="upload-demo"
                        ref="upload"
                        action="/api/successCase/updateCase"
                        :file-list="fileList"
                        :headers="getHeader"
                        :data=form1
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
          dialogForm2Visible: false,
          fileList:[],
          form1: {
            case_id: '',
            case_title: '',
            case_subhead: '',
            case_text: '',
            file_url : ''
          },
          form: {
            selectIndexID: '',
            selectBusinessID: ''
          },
          form2: {
            case_id: '',
            index: '',
            is_use: ''
          },
          lastSelect:'',
          select_row : {},
          showIndexData : [],
          showBusinessData : [],
          check: ['案例编号', '案例图片', '案例标题', '案例副标题', '案例内容', '主页使用','业务使用','状态', '日期'],
          pageNum : 1,
          pageSize : 5,
          total: 0,
          vertical: 'apple',
          value1: '',
          select: '',
          columns: [
                  {
                      title: '案例编号',
                      key: 'icase_id',
                      height: 250
                  },
                  {
                      title: '案例图片',
                      key: 'icase_img',
                      height: 200
                  },
                  {
                      title: '案例标题',
                      key: 'icase_title',
                      height: 200
                  },
                  {
                      title: '案例副标题',
                      key: 'icase_subhead',
                      height: 200
                  },
                  {
                      title: '案例内容',
                      key: 'icase_text',
                      height: 200
                  },
                  {
                      title: '主页使用',
                      key: 'index_use',
                      height: 200
                  },
                  {
                      title: '业务使用',
                      key: 'business_use',
                      height: 200
                  },
                  {
                      title: '状态',
                      key: 'icase_status',
                      height: 200
                  },
                  {
                      title: '日期',
                      key: 'icase_create_time',
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
          case_id: row.icase_id,
          index: '',
          is_use: '0'
        }
      },
      use(row){
          this.dialogForm2Visible = true
          this.form2= {
            case_id: row.icase_id,
            index: '',
            is_use: '1'
          }
      },
      confirm2(){
        this.dialogForm2Visible = false
        https.fetchPost("/api/successCase/changeStatus",this.form2).then((data) => {
                      if(data.data.status != 20000){
                        this.onError(data.data)
                      }else{
                        this.getData()
                        this.dialogForm2Visible=false
                        this.form2= {
                          case_id: '',
                          index: '',
                          is_use: ''
                        }
                        this.success()
                      }
                    })
                    .catch((err)=>{
                        this.onError(err)

                    })
      },
      selectOne (selection, row){
        this.lastSelect = row.icase_id;
      },
      isContain(text){
        for( var i in this.check){
          if(text == this.check[i]){
            return true
          }
        }
        return false
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
         this.form1= {
           case_id: '',
           case_title: '',
           case_subhead: '',
           case_text: '',
           file_url : ''
         }
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
      addFile(file,fileList){
        this.fileList = fileList
      },
      submitUpload(){
        if(this.fileList.length == 0){
          https.fetchPost("/api/successCase/updateCase",this.form1).then((data) => {
                        if(data.data.status != 20000){
                          this.onError(data.data)
                        }else{
                          this.getData()
                          this.dialogForm1Visible=false
                          this.form1= {
                            case_id: '',
                            case_title: '',
                            case_subhead: '',
                            case_text: '',
                            file_url : ''
                          }
                          this.success()
                        }
                      })
                      .catch((err)=>{
                          this.onError(err)

                      })
        }else{
           this.$refs.upload.submit();
        }
      },
      close(){
        this.dialogFormVisible = false
        this.select_row = {}
        this.form.selectIndexID = ''
        this.form.selectBusinessID = ''
      },
      conso(){
        this.dialogForm2Visible = false
        this.form2= {
          case_id: '',
          index: '',
          is_use: ''
        }
        this.dialogFormVisible = false
        this.select_row = {}
        this.form.selectIndexID = ''
        this.form.selectBusinessID = ''
      },
      confirm(){
        https.fetchPost("/api/successCase/updateStatus",{'index_old_case_id':this.form.selectIndexID ,'case_id':this.select_row.icase_id,
        'bus_old_case_id':this.form.selectBusinessID}).then((data) => {
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
        this.select_row = {}
        this.form.selectIndexID = ''
        this.form.selectBusinessID = ''
      },
      tran(row){
        this.select_row = row
        this.dialogFormVisible = true
        if(row.index_use != 1){
          https.fetchGet("/api/successCase/getIndexCases").then((data) => {
                          if(data.data.status != 20000){
                            this.onError(data.data)
                          }else{
                            this.showIndexData = data.data.data
                          }

                          //console.log('thenthenthenthen',data.data.data)
                      })
                      .catch((err)=>{
                          this.onError(err)
                      })
        }
        if(row.business_use != 1){
          https.fetchGet("/api/successCase/getBusinessCases").then((data) => {
                          if(data.data.status != 20000){
                            this.onError(data.data)
                          }else{
                            this.showBusinessData = data.data.data
                          }
                      })
                      .catch((err)=>{
                          this.onError(err)
                      })
        }
      },
      tranform1(row){
        this.fileList=[]
        this.dialogForm1Visible = true
        this.form1= {

          case_id :row.icase_id,
          case_title: row.icase_title,
          case_subhead: row.icase_subhead,
          case_text : row.icase_text,
          file_url : row.icase_img,
        }
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
      rowStyle({row, rowIndex}) {
         if (row.icase_status === 0) {
           return 'background-color: #FDF5E6';
         }
         return '';
       },
      getData(){
        https.fetchGet("/api/successCase/getCases",{'pageNum': this.pageNum, 'pageSize': this.pageSize}).then((data) => {
                        if(data.data.status != 20000){
                          this.onError(data.data)
                        }else{
                          this.tableData = data.data.data.list
                          this.total = data.data.data.total
                        }
                    })
                    .catch((err)=>{
                        this.$router.push({path: '/login'})

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
