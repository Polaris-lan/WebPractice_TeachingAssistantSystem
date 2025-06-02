<template>
  <div class="task-publish">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>发布新任务</span>
        </div>
      </template>
      
      <div class="card-content">
        <!-- 教师信息展示 -->
        <el-alert 
          title="当前教师信息" 
          type="info" 
          :closable="false"
          class="teacher-info"
        >
          <p>姓名: {{ professor.name }}</p>
          <p>工号: {{ professor.num }}</p>
        </el-alert>

        <!-- 任务发布表单 -->
        <el-form 
          ref="taskForm" 
          :model="taskForm" 
          label-width="100px"
          class="task-form"
        >
          <!-- 课程选择 -->
          <el-form-item label="选择课程" prop="course">
            <el-select 
              v-model="taskForm.course" 
              placeholder="请选择课程"
              @click="handleCourseChange"
              
            >
              <el-option
                v-for="course in professor.subject.split(',')"
                :key="course"
                :label="course"
                :value="course"
              />
            </el-select>
          </el-form-item>

          <!-- 班级选择 -->
          <el-form-item label="选择班级" prop="class">
            <el-select 
              v-model="taskForm.class" 
              placeholder="请选择班级"
              :disabled="!taskForm.course"
            >
              <el-option
                v-for="classItem in availableClasses"
                :key="classItem"
                :label="classItem"
                :value="classItem"
              />
            </el-select>
          </el-form-item>

          <!-- 任务标题 -->
          <el-form-item label="任务标题" prop="title">
            <el-input 
              v-model="taskForm.title" 
              placeholder="请输入任务标题"
            />
          </el-form-item>

          <!-- 任务内容 -->
          <el-form-item label="任务内容" prop="content">
            <el-input
              v-model="taskForm.content"
              type="textarea"
              :rows="4"
              placeholder="请输入任务详细内容"
            />
          </el-form-item>

          <!-- 文件上传 -->
          <el-form-item label="任务附件">
            <el-upload
              class="upload-demo"
              action="http://localhost:8080/api/tasks/upload"
              :on-success="handleFileSuccess"
              :before-upload="beforeFileUpload"
              :limit="3"
              :file-list="taskForm.files"
            >
              <el-button type="primary">点击上传</el-button>
              <template #tip>
                <div class="el-upload__tip">
                  支持上传PDF/Word/Excel文件，单个文件不超过5MB
                </div>
              </template>
            </el-upload>
          </el-form-item>

          <!-- 提交按钮 -->
          <el-form-item>
            <el-button 
              type="primary" 
              @click="submitTask"
              :loading="submitting"
            >
              发布任务
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'
import { useRoute } from 'vue-router'

const route=useRoute();
const token=route.query.uname;
// 教师信息
const professor = ref({
  num: '',
  name: '',
  subject: '', // 格式："数学,物理,化学"
  classnum: '' // 格式："班级1,班级2,班级3"
})

// 任务表单
const taskForm = ref({
  course: '',
  class: '',
  title: '',
  content: '',
  files: []
})

// 可用班级列表（根据课程筛选）
const availableClasses = ref<string[]>([])

// 提交状态
const submitting = ref(false)

// 获取教师信息
const fetchProfessorInfo = async () => {
  try {
    const response = await axios.get("http://localhost:8080/info",{params:{token:route.query.uname}})
    professor.value = response.data
    //console.log(professor.value)
  } catch (error) {
    ElMessage.error('获取教师信息失败')
    console.error(error)
  }
}

// 课程变化处理
const handleCourseChange = (selectedCourse: string) => {
  
  console.log(selectedCourse)
  
  taskForm.value.course = selectedCourse;
  taskForm.value.class = '' // 清空已选班级
  // 这里可以根据课程筛选班级，示例直接使用所有班级
  availableClasses.value = professor.value.classnum.split(',')
  console.log(availableClasses)
}

// 文件上传前验证
const beforeFileUpload = (file: File) => {
  const allowedTypes = [
    'application/pdf',
    'application/msword',
    'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
    'application/vnd.ms-excel',
    'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
  ]
  const isAllowed = allowedTypes.includes(file.type)
  const isLt5M = file.size / 1024 / 1024 < 5

  if (!isAllowed) {
    ElMessage.error('只支持上传PDF/Word/Excel文件!')
  }
  if (!isLt5M) {
    ElMessage.error('文件大小不能超过5MB!')
  }

  return isAllowed && isLt5M
}

// 文件上传成功处理
const handleFileSuccess = (response: any, file: any) => {

  // if (response.code === 200) {
  //   taskForm.value.files.push({
  //     name: file.name,
  //     url: response.data.url
  //   })
    ElMessage.success('文件上传成功')
  // } else {
  //   ElMessage.error(response.message || '文件上传失败')
  // }
}

// 提交任务
const submitTask = async () => {
  submitting.value = true
  // try {
  //   const response = await axios.post('/api/tasks/publish', {
  //     professorNum: professor.value.num,
  //     ...taskForm.value
  //   })
    
  //   if (response.data.success) {
  //     ElMessage.success('任务发布成功')
  //     // 重置表单
  //     taskForm.value = {
  //       course: '',
  //       class: '',
  //       title: '',
  //       content: '',
  //       files: []
  //     }
  //   } else {
  //     ElMessage.error(response.data.message || '任务发布失败')
  //   }
  // } catch (error) {
  //   ElMessage.error('任务发布出错')
  //   console.error(error)
  // } finally {
  //   submitting.value = false
  // }
  ElMessage.success('任务发布成功')
      taskForm.value = {
        course: '',
        class: '',
        title: '',
        content: '',
        files: []
      }
}

// 初始化获取教师信息
onMounted(() => {
  fetchProfessorInfo()
})
</script>

<style scoped>
.task-publish {
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

.teacher-info {
  margin-bottom: 20px;
}

.teacher-info p {
  margin: 5px 0;
}

.task-form {
  max-width: 800px;
}

.upload-demo {
  width: 100%;
}
</style>