<template>
  <div class="dashboard">
    
    <el-card class="profile-card">
      <div class="profile-header">
        <div class="avatar-container">
          <el-avatar :size="120" :src="professor.photo || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
          <el-upload
            class="avatar-uploader"
            name="file" 
            action="http://localhost:8080/uploadphoto"
            :headers="{ 'Authorization': token }"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <el-button type="primary" size="small" class="upload-btn">更换头像</el-button>
          </el-upload>
        </div>
        <div class="profile-info">
          <h3>{{ professor.name }}</h3>
          <p class="professor-id">工号: {{ professor.num }}</p>
        </div>
      </div>

      <el-divider />

      <div class="profile-details">
        <el-descriptions :column="1" border>
          <el-descriptions-item label="教授课程">{{ professor.subject }}</el-descriptions-item>
          <el-descriptions-item label="授课班级">{{ professor.classnum }}</el-descriptions-item>
          <el-descriptions-item label="联系电话">{{ professor.phone || '未设置' }}</el-descriptions-item>
          <el-descriptions-item label="电子邮箱">{{ professor.email || '未设置' }}</el-descriptions-item>
        </el-descriptions>
      </div>

      <div class="profile-actions">
        <el-button type="primary" @click="editDialogVisible = true">编辑个人信息</el-button>
      </div>
    </el-card>

    <!-- 编辑对话框 -->
    <el-dialog v-model="editDialogVisible" title="编辑个人信息" width="500px">
      <el-form :model="editForm" label-width="100px">
        <el-form-item label="工号">
          <el-input v-model="editForm.num" disabled />
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="editForm.name" />
        </el-form-item>
        <el-form-item label="教授课程">
          <el-input v-model="editForm.subject" />
        </el-form-item>
        <el-form-item label="授课班级">
          <el-input v-model="editForm.classnum" />
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="editForm.phone" />
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="editForm.email" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveProfile">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
//import { useMyUserStore } from '@/stores/MyUser';
import { useRoute } from 'vue-router';

let route=useRoute();

// 教师信息
const professor = ref({
  num: '',
  name: '',
  subject: '',
  classnum: '',
  photo: '',
  phone: '',
  email: ''
})

// 编辑表单
const editForm = ref({ ...professor.value })
const editDialogVisible = ref(false)
const username = route.query.uname; // 这里要和登录页面传递的参数名一致

const token=route.query.uname;

// 获取教师信息
const fetchProfessorInfo=async()=>{
  try {
    // 确保获取正确的query参数
    
    
    if (!username) {
      ElMessage.error('未获取到用户信息');
      return;
    }

    const response = await axios.get("http://localhost:8080/info", {
      params: { 
        token: username // 或者直接使用 username 作为参数
      }
    });
    
    professor.value = response.data;
    editForm.value = { ...professor.value };
  } catch (error) {
    ElMessage.error('获取教师信息失败');
    console.error(error);
  }
}

// 保存个人信息
const saveProfile = async () => {
  try {
    //const username=route.query.uname;
    await axios.post("http://localhost:8080/update", editForm.value)
    ElMessage.success('保存成功')
    fetchProfessorInfo() // 刷新数据
    editDialogVisible.value = false
  } catch (error) {
    ElMessage.error('保存失败')
    console.error(error)
  }
}

// 头像上传成功处理
const handleAvatarSuccess = (response: any) => {
  if (response.code === 200) {
    //professor.value.photo = response.data.url
    //professor.value.photo = `D:/ta-system/${response.data.filename}`
    //可以放在vue的public里直接"/public"来显示
    professor.value.photo="/public/10101010_1748617208296.png"
    ElMessage.success('头像上传成功')
  } else {
    ElMessage.error(response.message || '头像上传失败')
  }
}



// 头像上传前验证
const beforeAvatarUpload = (file: File) => {
  const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
  const isLt2M = file.size / 1024 / 1024 < 2

  if (!isJpgOrPng) {
    ElMessage.error('头像图片只能是 JPG/PNG 格式!')
  }
  if (!isLt2M) {
    ElMessage.error('头像图片大小不能超过 2MB!')
  }

  return isJpgOrPng && isLt2M
}

// 组件挂载时获取数据
onMounted(() => {
  fetchProfessorInfo()
})
</script>

<style scoped>
.dashboard {
  padding: 20px;
}

.profile-card {
  margin-top: 20px;
  max-width: 800px;
  margin-left: auto;
  margin-right: auto;
}

.profile-header {
  display: flex;
  align-items: center;
  padding: 20px;
}

.avatar-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-right: 40px;
}

.upload-btn {
  margin-top: 10px;
}

.profile-info h3 {
  margin: 0;
  font-size: 24px;
  color: #303133;
}

.profile-id {
  margin: 5px 0 0;
  color: #909399;
}

.profile-details {
  padding: 0 20px 20px;
}

.profile-actions {
  display: flex;
  justify-content: center;
  padding: 20px;
}

:deep(.el-descriptions__body) {
  background-color: #f5f7fa;
}

:deep(.el-descriptions__label) {
  width: 120px;
}
</style>