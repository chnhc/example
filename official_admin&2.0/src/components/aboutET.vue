<template>
  <div class="add" :style="{height:mHeight}">

    <el-form ref="form" :model="form" label-width="120px">
        <div class="ad">经验措施</div>
        <el-form-item label="经验措施标题">
          <el-input v-model="form.etitle"  placeholder="输入20个字符以内" clearable></el-input>
        </el-form-item>
        <el-form-item label="经验措施副标题">
          <el-input v-model="form.esubhead" placeholder="输入20个字符以内" clearable></el-input>
        </el-form-item>

        <div class="ad">团队</div>
        <el-form-item label="团队标题">
          <el-input v-model="form.ttitle" maxlength="20" placeholder="输入20个字符以内" clearable></el-input>
        </el-form-item>
        <el-form-item label="团队副标题">
          <el-input v-model="form.tsubhead" maxlength="20" placeholder="输入20个字符以内" clearable></el-input>
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
                  etitle: '',
                  esubhead: '',
                  ttitle: '',
                  tsubhead: '',
                }
            }
        },
        methods: {

           submitUpload(){
             https.fetchPost("/api/aboutUS/updateIndexET",this.form).then((data) => {

                           if(data.data.status != 20000){
                             this.onError(data.data)
                           }else{
                             this.form= {
                               etitle: '',
                               esubhead: '',
                               ttitle: '',
                               tsubhead: '',
                             }
                             this.onSuccess()
                           }
                         })
                         .catch((err)=>{
                             this.onError(err)

                         })
           },
           onSuccess(){
             this.form= {
               etitle: '',
               esubhead: '',
               ttitle: '',
               tsubhead: '',
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
