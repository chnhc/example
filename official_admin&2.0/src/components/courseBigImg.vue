<template>
  <div class="add" :style="{height:mHeight}">

    <el-form ref="form" :model="form" label-width="100px">

      <el-form-item label="图片网络地址">
        <el-input v-model="form.file_url" placeholder="输入图片地址" clearable></el-input>
      </el-form-item>
        <el-form-item label="更换图片">
              <el-upload
                class="upload-demo"
                ref="upload"
                action="/api/courseFile/updateCourseFileBigImg"
                :file-list="fileList"
                :on-success="onSuccess"
                :headers="getHeader"
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
                baseHttp: baseHttp,
                fileList:[],
                form: {
                  file_url : ''
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
            if(this.fileList.length == 0){
              https.fetchPost("/api/courseFile/updateCourseFileBigImg",this.form).then((data) => {
                            if(data.data.status != 20000){
                              this.onError(data.data)
                            }else{
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
            this.form= {
              file_url : ''
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
