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
         width="100">
         <template slot-scope="scope">
           <el-button type="text" size="small" @click="tranform(scope.row)" >编辑</el-button>
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

      <el-dialog title="修改操作" :visible.sync="dialogFormVisible" @close="close">
        <el-form ref="form" :model="form" label-width="90px">
            <el-form-item label="文档标题">
              <el-input v-model="form.file_context_title" placeholder="输入20个字符以内" clearable></el-input>
            </el-form-item>
            <el-form-item label="显示内容">
              <el-input type="textarea" v-model="form.file_context_desc"></el-input>
            </el-form-item>

            <el-form-item label="文件">
                  <el-upload
                    class="upload-demo"
                    ref="upload"
                    action="/api/courseFile/updateFile"
                    :file-list="fileList"
                    :data=form
                    :headers="getHeader"
                    :on-success="onSuccess"
                    :on-error="onError"
                    :on-change="addFile"
                    :auto-upload="false">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    <div slot="tip" class="el-upload__tip">上传文件，且不超过20MB</div>
                  </el-upload>
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
          fileList:[],
          form: {
            file_id:'',
            file_context_title: '',
            file_context_desc: ''
          },
          check: ['文档编号','文档地址','文档下载地址','文档标题','文档内容','状态','日期'],
          pageNum : 1,
          pageSize : 5,
          total: 0,
          vertical: 'apple',
          value1: '',
          select: '',
          columns: [
                  {
                      title: '文档编号',
                      key: 'file_id',
                      height: 250
                  },
                  {
                      title: '文档地址',
                      key: 'file_url',
                      height: 200
                  },
                  {
                      title: '文档下载地址',
                      key: 'file_download',
                      height: 200
                  },
                  {
                      title: '文档标题',
                      key: 'file_context_title',
                      height: 200
                  },
                  {
                      title: '文档内容',
                      key: 'file_context_desc',
                      height: 200
                  },
                  {
                      title: '状态',
                      key: 'file_status',
                      height: 200
                  },
                  {
                      title: '日期',
                      key: 'file_create_time',
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
        var form2= {
          file_id: row.file_id
        }
        https.fetchPost("/api/courseFile/deleteFile",form2).then((data) => {
                      if(data.data.status != 20000){
                        this.onError(data.data)
                      }else{
                        this.getData()
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
         if (row.file_status === 0) {
           return 'background-color: #FDF5E6';
         }
         return '';
       },
       addFile(file,fileList){
         this.fileList = fileList
       },
        submitUpload(){
          if(this.fileList.length == 0){
            https.fetchPost("/api/courseFile/updateFile",this.form).then((data) => {
                            if(data.data.status != 20000){
                              this.onError(data.data)
                            }else{
                              this.getData()
                              this.dialogFormVisible=false
                              this.form= {
                                file_id:'',
                                file_context_title: '',
                                file_context_desc: ''
                              },
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
           this.dialogFormVisible=false
           this.form= {
             file_id:'',
             file_context_title: '',
             file_context_desc: ''
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
      getData(){
        https.fetchGet("/api/courseFile/getFile",{'pageNum': this.pageNum, 'pageSize': this.pageSize}).then((data) => {

                        if(data.data.status != 20000){
                          this.onError(data.data)
                        }else{
                          this.tableData = data.data.data.list
                          this.total = data.data.data.total
                        }
                    })
                    .catch((err)=>{
                        console.log(err)

                    })
      },
      close(){
        this.dialogFormVisible = false
      },
      tranform(row){
        this.fileList =[]
        this.dialogFormVisible=true
        this.form= {
          file_id: row.file_id,
          file_context_title: row.file_context_title,
          file_context_desc: row.file_context_desc
        }
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
