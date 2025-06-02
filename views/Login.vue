<script setup lang="ts">
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
//import{useMyUserStore}from '@/stores/MyUser';

    let username=ref('');
    let password=ref('');
    let message=ref('');
    let id=ref('');
    let router=useRouter();
    //let token=ref('');
    // let stores=useMyUserStore();

      function Login(){
        let user={
          id:id.value,
          name:username.value,
          password:password.value
        }
        axios.post("http://localhost:8080/login",user).then(res=>{
         if(res.data){
          ElMessage.success("登录成功！");
          
          router.push({
            path:'/home',
            query:{uname:username.value}
          });

          //router.push('/home');
          // router.push({
          //   path:'/home/dashboard',
          //   query:{uname:username.value}
          // });

          // router.push({
          //   path:'/home/taskpublish',
          //   query:{uname:username.value}
          // });
         }else{
          ElMessage.error("登录失败，账号或密码错误！")
          username.value='';
          password.value='';
         }

      })
    }

</script>

<template>

    <div class="login-container">
    <h1>登录页面</h1>
    <form @submit.prevent="Login">
      <div class="form-group">
        <label for="username">账号:</label>
        <el-input
          v-model="username"
          style="width: 90%"
          placeholder="请输入用户名"
          clearable
        />
      </div>
      <div class="form-group">
        <label for="password">密码:</label>
        <el-input
          v-model="password"
          style="width: 90%"
          type="password"
          placeholder="请输入密码"
          show-password
        />
      </div>
      <button type="submit">登录</button>
    </form>
    <p v-if="message" >{{ message }}</p> 
  </div>

</template>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
h1 {
  text-align: center;
  color: #333;
}
.form-group {
  margin-bottom: 15px;
}

label {
  margin-bottom: 5px;
  font-weight: bold;
}

/* input {
  width: 90%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
} */


button {
  width: 100%;
  padding: 10px;
  background-color: #5976b9;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}
button:hover {
  background-color: #36549f;
}

p {
    text-align: center;
}
</style>
