<template>
  <div class="add" :style="{height:mHeight}">

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
                action="/api/courseFile/insertFile"
                :file-list="fileList"
                :data=form
                :headers="getHeader"
                :on-success="onSuccess"
                :on-error="onError"
                :on-change="addFile"
                accept=".pdf,.PDF"
                :auto-upload="false">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <div slot="tip" class="el-upload__tip">上传PDF文件，且不超过20MB</div>
              </el-upload>
        </el-form-item>


          <el-button style="margin-left: 10px;" size="small" type="primary" @click="submitUpload">提交到服务器</el-button>
        </el-form-item>
      </el-form>
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
                fileList:[],
                form: {
                  file_context_title: '',
                  file_context_desc: ''
                }
            }
        },
        computed:{
          getHeader(){
            var store = require('store')
            return {'Authorization':'bearer '+store.get('token')}
          }
        },
        methods: {
          addFile(file,fileList){
            this.fileList = fileList
          },
           submitUpload(){
             this.$refs.upload.submit();
           },
           success(){
             this.form= {
               file_context_title: '',
               file_context_desc: ''
             }
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
           }

        },
        mounted () {

        }
    }
</script>
<style scoped>
.add{
  padding: 20px;
  width: 80%;
}
</style>
