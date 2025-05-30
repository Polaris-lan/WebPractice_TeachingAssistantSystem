<template>
  <div class="dashboard">
    <h2>我的主页</h2>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>欢迎使用助教系统</span>
        </div>
      </template>
      <div class="card-content">
        <p>这里是助教系统的首页，您可以开始您的工作了！</p>
      </div>
    </el-card>
  </div>
</template>

<script setup lang="ts">


</script>

<style scoped>
.dashboard {
  padding: 20px;
}

.box-card {
  margin-top: 20px;
}

.card-header {
  font-size: 18px;
  font-weight: bold;
}

.card-content {
  padding: 10px 0;
}
</style>