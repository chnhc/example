<template>
  <div class="add" :style="{height:mHeight}">

    <el-form ref="form" :model="form" label-width="90px">
        <el-form-item label="大图标题">
          <el-input v-model="form.big_title" maxlength="20" placeholder="输入20个字符以内" clearable></el-input>
        </el-form-item>
        <el-form-item label="大图副标题">
          <el-input v-model="form.big_subhead" maxlength="20" placeholder="输入20个字符以内" clearable></el-input>
        </el-form-item>
        <el-form-item label="大图说明">
          <el-input v-model="form.big_desc" maxlength="20" placeholder="输入20个字符以内" clearable></el-input>
        </el-form-item>
        <el-form-item label="更换大图">
              <el-upload
                class="upload-demo"
                ref="upload"
                action="http://localhost:9999/index/file"
                :file-list="fileList"
                :data=form.data
                :auto-upload="false">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
        </el-form-item>

        <el-form-item label="活动形式">
          <el-input type="textarea" v-model="form.desc"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
        </el-form-item>
      </el-form>
  </div>
</template>
<script>
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
                  data:{name:'ali',age:'123'},
                  big_title: '',
                  big_subhead: '',
                  big_desc: '',
                  desc: ''
                }
            }
        },
        methods: {
          onSubmit() {
             console.log('submit!');
           },
           submitUpload(){
             this.$refs.upload.submit();
           },

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
