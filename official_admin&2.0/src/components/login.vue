<template>
  <div class="login" :style="{height:WHeight}">
    <div class="contain" :style="{backgroundImage:'url('+require('../assets/bg1.jpg')+')'}">
      <div class="transparent">

        <div class="form_contain">
          <div class="title"> OfficeAdmin</div>
          <div class="text"> 官方后台管理模板系统</div>

            <Form ref="formInline" :model="formInline" :rules="ruleInline" class="form">
                <FormItem prop="user">
                    <Input type="text" v-model="formInline.username" placeholder="用户名">
                        <Icon type="ios-person-outline" slot="prepend"></Icon>
                    </Input>
                </FormItem>
                <FormItem prop="password">
                    <Input type="password" v-model="formInline.password" placeholder="密码">
                        <Icon type="ios-lock-outline" slot="prepend"></Icon>
                    </Input>
                </FormItem>
                <FormItem style="  text-align: left;">
                    <Button type="primary" style="margin-top: 10px;" @click="submit" >登录</Button>
                </FormItem>
            </Form>

        </div>

      </div>
    </div>

  </div>
</template>

<script>
import https from '../https.js'
export default {
  props: {
    mHeight: {
      type: String,
      default: "1000px"
    },
    WHeight: {
      type: String,
      default: "1000px"
    }
  },
  data () {
      return {
        formInline: {
                    grant_type : 'password',
                    username: '',
                    password: ''
                },
        ruleInline: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { type: 'string', min: 6, message: '最少6位数', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { type: 'string', min: 6, message: '最少6位数', trigger: 'blur' }
                    ]
                }

      }

  },
  methods:{
    onSuccess(){
      this.$message({
         duration: 1000,
         message: '操作成功',
         type: 'success'
       });
    },
    onError(err){

      this.$message({
        duration: 1000,
        message: '用户名或者密码错误',
        type: 'warning'
      });
    },
    submit(){
      var store = require('store')
      store.remove('token');
      https.fetchPost("/api/oauth/token",this.formInline,{'Authorization':'Basic YWRtaW46e25vb3B9YWRtaW4='}).then((data) => {

                        this.onSuccess()
                        store.set('token',data.data.tokenEntity.value);
                        this.$router.push({path: '/'})

                  })
                  .catch((err)=>{
                        this.onError(err)

                  })
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login{
  width: 100%;
}
.contain{
  width: 100%;
  height: 100%;
  background-size: cover;
  background-attachment: fixed;
}
.transparent{
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.2);
}
.form_contain{
  width: 350px;
  background-color: rgba(255,255,255,0.5);
  height: auto;
  margin: auto;
  padding-top: 100px;
}
.title{
  color: #000;
  font-size: 25px;
  text-align: center;
}
.text{
  color: #808080;
  font-size: 18px;
  text-align: center;
}
.form{
  width: 80%;
  margin: 20px auto ;
  padding-bottom: 20px;

}
</style>
