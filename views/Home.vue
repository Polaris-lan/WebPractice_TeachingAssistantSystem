<!-- <template>
	<div class="common-layout">
	    <el-container>
	      <el-header>学生管理系统</el-header>
	      <el-container>
	        <el-aside width="200px">
			  <el-menu
				default-active="2"
				class="el-menu-vertical-demo"
				router
			  >
				<el-menu-item index="/student">
				  <el-icon><Service /></el-icon>
				  <span>学生信息管理</span>
				</el-menu-item>
				<el-menu-item index="/a">
				  <el-icon><document /></el-icon>
				  <span>学生寝室管理</span>
				</el-menu-item>
				<el-menu-item index="/b">
				  <el-icon><setting /></el-icon>
				  <span>学生成绩管理</span>
				</el-menu-item>
			  </el-menu>
			</el-aside>
	        <el-main>
				<router-view></router-view>
			</el-main>
	      </el-container>
	    </el-container>
	  </div>
</template>

<script setup lang="ts">
</script>

<style scoped>
	  .el-header{
	    background-color: #B3C0D1;
	    color: #333;
	    text-align: center;
	    line-height: 60px;
		font-size: 30px;
	  }
	  
	  .el-aside {
	    background-color: #D3DCE6;
	    color: #333;
	    text-align: center;
	    line-height: 200px;
		height: 500px;
	  }
	  
	  .el-main {
	    background-color: #E9EEF3;
	    color: #333;
	    text-align: center;
	    line-height: 60px;
	  }
	  
	  body > .el-container {
	    margin-bottom: 40px;
	  }
</style> -->



<template>
  <div class="home-container">
    <el-header class="header">
      <h1>助教系统</h1>
    </el-header>
    
    <el-container class="main-container">
      <el-aside width="200px" class="sidebar">
        <el-menu
          router
          default-active="/home/dashboard"
          class="el-menu-vertical"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-menu-item @click="EnterDashboard" index="/home/dashboard">
            <el-icon><House /></el-icon>
            <span>我的主页</span>
          </el-menu-item>
          <el-menu-item @click="EnterStudentManagement" index="/home/student">
            <el-icon><User /></el-icon>
            <span>学生信息管理</span>
          </el-menu-item>
          <el-menu-item @click="EnterTaskPublish" index="/home/taskpublish">
            <el-icon><Document /></el-icon>
            <span>任务发布</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      
      <el-main class="content-area">
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import { House, User, Document } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
let route=useRoute();
const name=route.query.uname;

function EnterDashboard(){
	//router.push('/dashboard');

	router.push({
		path:'/home/dashboard',
		query:{uname:name}
	})
}

function EnterStudentManagement(){
	router.push('/student');
}

function EnterTaskPublish(){
	//router.push('/taskpublish');

	router.push({
		path:'/taskpublish',
		query:{uname:name}
	})
}

</script>

<style scoped>
.home-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  background-color: #5976b9;
  color: white;
  display: flex;
  align-items: center;
  padding-left: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
}

.header h1 {
  margin: 0;
  font-size: 24px;
}

.main-container {
  flex: 1;
  overflow: hidden;
}

.sidebar {
  background-color: #82919f;
  height: 100%;
}

.el-menu-vertical {
  border-right: none;
  height: 100%;
}

.content-area {
  padding: 20px;
  background-color: #f5f7fa;
}
</style>