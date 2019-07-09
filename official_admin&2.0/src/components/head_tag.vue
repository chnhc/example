<template>
  <div id="headTag">
    <div :class="[ tag_index == selectHeadTag? 'active tag' : 'noactive tag']" @click="selectMenu">
        {{tag_text}}
        <Icon type="md-close" size="16" class="tag_icon" @click="removeMenu" />
    </div>
  </div>
</template>

<script>
  export default {
      props: {
        selectHeadTag: {
          type: String,
          default: "-1"
        },
        tag_text: {
          type: String,
          default: "tag"
        },
        tag_index: {
          type: String,
          default: "-2"
        },
        index: {
          type: Number,
          default: 0
        }
      },
      data () {
          return {
            msg : "msg"
          }
      },
      methods:{
        isActive(){

          if(this.selectHeadTag != "0"){
            if(this.tag_index == this.selectHeadTag ){
              console.log("aaa");
                return true
            }
          }else{
            return false
          }
        },
        removeMenu(){
          this.$emit("removeMenu",this.tag_index,this.index)
        },
        selectMenu(){
          this.$emit("selectMenu",this.tag_index,this.index)
        }
      }

  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#headTag{

}
.tag:hover{
  background-color: #DEDEDE;
}
.tag_icon:hover{
  background-color: red;
  color: #DEDEDE;
}
.active{
  background-color: #DEDEDE;
}
.noactive{
  background-color: #fff;
}
.tag_icon{
  float: right;
  line-height: 37px;
}
.tag{
  cursor: pointer;
  height: 35px;
  width: 80px;
  text-align: center;
  line-height: 37px;

  border-right:1px solid #DEDEDE;
}
</style>
