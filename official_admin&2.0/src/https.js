import axios from 'axios'
import qs from 'qs'

axios.defaults.timeout = 5000;                        //响应时间
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';        //配置请求头
axios.defaults.baseURL = '';   //配置接口地址
var store = require('store')
//POST传参序列化(添加请求拦截器)
axios.interceptors.request.use((config) => {
    //在发送请求之前做某件事
    if(config.method  === 'post'){
        config.data = qs.stringify(config.data);
    }
    return config;
},(error) =>{
    console.log('错误的传参')
    return Promise.reject(error);
});

//返回状态判断(添加响应拦截器)
axios.interceptors.response.use((res) =>{
    if(typeof(res.data.newToken) != 'undefined'){
      store.set('token',res.data.newToken)
    }
    //对响应数据做些事
    if(!res.data.success){
        return Promise.resolve(res);
    }
    return res;
}, (error) => {
    console.log(error);
    console.log('网络异常')
    return Promise.reject(error);
});

//返回一个Promise(发送post请求)
export function fetchPost(url, params, header) {

    if(typeof(store.get('token')) != 'undefined'){
      if(typeof(header) == 'undefined'){
        header = {'Authorization':'bearer '+store.get('token')}
      }else{
        if(typeof(header.Authorization) == 'undefined'){
          header.Authorization = 'bearer '+store.get('token')
        }
      }
    }
    return new Promise((resolve, reject) => {

        axios.post(url, params, {headers:header})
            .then(response => {
                resolve(response);
            }, err => {
                reject(err);
            })
            .catch((error) => {
                reject(error)
            })
    })
}
////返回一个Promise(发送get请求)
export function fetchGet(url, param, header) {
    console.log(header);
    if(typeof(store.get('token')) != 'undefined'){
      if(typeof(header) == 'undefined'){
        header = {'Authorization':'bearer '+store.get('token')}
      }else{
        if(typeof(header.Authorization) == 'undefined'){
          header.Authorization = 'bearer '+store.get('token')
        }
      }
    }

    return new Promise((resolve, reject) => {

        axios.get(url, {params: param, headers:header})
            .then(response => {
                resolve(response)
            }, err => {
                reject(err)
            })
            .catch((error) => {
                reject(error)
            })
    })
}
export default {
    fetchPost,
    fetchGet,
}
