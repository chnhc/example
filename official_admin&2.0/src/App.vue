<template>
  <div id="app">
       <router-view :mHeight= 'contentHeight' :WHeight= 'WHeight'></router-view>
  </div>
</template>

<script>
// 导入组件
export default {
  name: 'App',
  data () {
      return {
          WHeight: '0px',
          contentHeight: '0px',

      }
  },
  components:{

  },
  computed: {


  },
  methods: {



  },
  created(){
    this.WHeight= (document.documentElement.clientHeight || document.body.clientHeight) - 3 +"px";
    this.contentHeight= (document.documentElement.clientHeight || document.body.clientHeight) - 118 +"px";


  },
  mounted(){

    const that = this;
     window.addEventListener("resize", function() {
           return (() => {

                var windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
                that.WHeight= windowHeight - 3 +"px";
                that.contentHeight= windowHeight - 118 +"px";
             })();
         });
  },
   watch: {
       screenHeiget (val) {
           if (!this.timer) {
               this.screenHeiget = val
               this.timer = true
               let that = this
               setTimeout(function () {
                   // that.screenWidth = that.$store.state.canvasWidth
                   console.log(that.screenWidth)
                   that.init()
                   that.timer = false
               }, 2000)
           }
       }
   }

}
</script>

<style>

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 100%;
  height: 100%;
}


</style>
