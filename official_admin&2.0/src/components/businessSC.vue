<template>
  <div class="add" :style="{height:mHeight}">

    <el-form ref="form" :model="form" label-width="90px">
        <div class="ad">服务</div>
        <el-form-item label="服务标题">
          <el-input v-model="form.stitle"  placeholder="输入20个字符以内" clearable></el-input>
        </el-form-item>
        <el-form-item label="服务副标题">
          <el-input v-model="form.ssubhead"  placeholder="输入20个字符以内" clearable></el-input>
        </el-form-item>

        <div class="ad">案例</div>
        <el-form-item label="案例标题">
          <el-input v-model="form.ctitle"  placeholder="输入20个字符以内" clearable></el-input>
        </el-form-item>
        <el-form-item label="案例副标题">
          <el-input v-model="form.csubhead"  placeholder="输入20个字符以内" clearable></el-input>
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
                  stitle: '',
                  ssubhead: '',
                  ctitle: '',
                  csubhead: '',
                }
            }
        },
        methods: {

           submitUpload(){
             https.fetchPost("/api/business/updateIndexSC",this.form).then((data) => {
                           if(data.data.status != 20000){
                             this.onError(data.data)
                           }else{
                             this.onSuccess()
                           }
                         })
                         .catch((err)=>{
                             this.onError(err)

                         })
           },
           onSuccess(){
             this.form= {
               stitle: '',
               ssubhead: '',
               ctitle: '',
               csubhead: '',
             }
             this.$message({
                duration: 1000,
                message: '操作成功',
                type: 'success'
              });
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
.ad{
  font-size: 20px;
  padding: 15px;
}
</style>
