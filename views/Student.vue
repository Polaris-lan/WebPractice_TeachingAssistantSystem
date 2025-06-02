<template>
  <div class="student-management">


	<!-- 搜索表单 -->
    <div class="search-form">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="姓名">
          <el-input 
            v-model="searchForm.stuname" 
            placeholder="请输入姓名" 
            clearable
          />
        </el-form-item>
        <el-form-item label="班级">
          <el-input 
            v-model="searchForm.classnum" 
            placeholder="请输入班级" 
            clearable
          />
        </el-form-item>
        <el-form-item label="学号">
          <el-input 
            v-model="searchForm.stunum" 
            placeholder="请输入学号" 
            clearable
          />
        </el-form-item>
		<!-- 操作按钮区域 -->
        <el-form-item>
          <el-button type="primary":icon="Search" @click="handleSearch"></el-button>
          <el-button tyoe="primary":icon="RefreshRight" @click="resetSearch"></el-button>
		  <el-button type="primary":icon="Plus" @click="dialogVisible = true"></el-button>
        </el-form-item>
      </el-form>
    </div>



    <!-- 添加学生对话框 -->
    <el-dialog v-model="dialogVisible" title="添加学生信息" width="400">
      姓名:
      <el-input
        v-model="stuname"
        style="width: 240px; margin-bottom: 15px"
        placeholder="请输入姓名"
        clearable
      />
      <br />
      班级:
      <el-input
        v-model="classnum"
        style="width: 240px; margin-bottom: 15px"
        placeholder="请输入班级"
        clearable
      />
      <br />
      学号:
      <el-input
        v-model="stunum"
        style="width: 240px; margin-bottom: 15px"
        placeholder="请输入学号"
        clearable
      />
      <br />
      <el-button type="primary" @click="addstu">添加</el-button>
    </el-dialog>

    <!-- 修改学生对话框 -->
    <el-dialog v-model="updialogVisible" title="修改学生信息" width="400">
      姓名:
      <el-input
        v-model="upstuname"
        style="width: 240px; margin-bottom: 15px"
        placeholder="请输入姓名"
        clearable
      />
      <br />
      班级:
      <el-input
        v-model="upclassnum"
        style="width: 240px; margin-bottom: 15px"
        placeholder="请输入班级"
        clearable
      />
      <br />
      作业提交状态:
      <el-input
        v-model="uphomeworkstate"
        style="width: 240px; margin-bottom: 15px"
        placeholder="0-未提交，1-已提交"
        clearable
      />
      <br />
      <el-button type="primary" @click="upstu" align="center">修改</el-button>
    </el-dialog>

    <!-- 学生信息表格 -->
    <el-table :data="currentPageData" style="width: 100%" border>
      <el-table-column prop="id" label="序号" width="80" align="center" />
      <el-table-column prop="stuname" label="学生姓名" width="180" align="center" />
      <el-table-column prop="classnum" label="班级" width="120" align="center" />
      <el-table-column prop="stunum" label="学号" width="150" align="center" />
      <el-table-column prop="path" label="作业" width="170" align="center" />
      <el-table-column prop="homeworkstate" label="作业提交状态" width="150" align="center" />
      <el-table-column label="操作" width="180" align="center">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="updt(scope.row)">
            修改
          </el-button>
          <el-button link type="danger" size="small" @click="delstu(scope.row.stunum)">
            删除
          </el-button>

		  <!-- <el-dialog
			v-model="delstu"
			title="Tips"
			width="500"
			:before-close="handleClose"
		>
			<span>This is a message</span>
			<template #footer>
			<div class="dialog-footer">
				<el-button @click="dialogVisible = false">Cancel</el-button>
				<el-button type="primary" @click="dialogVisible = false">
				Confirm
				</el-button>
			</div>
			</template>
		</el-dialog> -->

        </template>
      </el-table-column>
    </el-table>

    <!-- 分页控件 -->
    <div class="pagination">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[5, 6, 7, 10]"
        :small="false"
        :background="true"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { Plus, RefreshRight, Search } from '@element-plus/icons-vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { ElMessageBox } from 'element-plus'

// 搜索表单
const searchForm = ref({
  stuname: '',
  classnum: '',
  stunum: ''
})

// 表格数据
let tableData = ref([])

// 分页相关
const currentPage = ref(1)
const pageSize = ref(7)

// 计算当前页显示的数据
const currentPageData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  return tableData.value.slice(start, end)
})

// 对话框相关
let dialogVisible = ref(false)
let updialogVisible = ref(false)

// 表单数据
let id = ref("")
let stuname = ref("")
let classnum = ref("")
let stunum = ref("")
let path = ref("")
let homeworkstate = ref("")

let upid = ref("")
let upstuname = ref("")
let upclassnum = ref("")
let upstunum = ref("")
let uppath = ref("")
let uphomeworkstate = ref("")



// 修改学生信息(在对话框里直接显示修改前学生信息)
function updt(row) {
  updialogVisible.value = true
  upid.value = row.id
  upstuname.value = row.stuname
  upclassnum.value = row.classnum
  upstunum.value = row.stunum
  uppath.value = row.path
  uphomeworkstate.value = row.homeworkstate
}

// 提交修改
function upstu() {
  let obj = {
    id: upid.value,
    stuname: upstuname.value,
    classnum: upclassnum.value,
    stunum: upstunum.value,
    path: uppath.value,
    homeworkstate: uphomeworkstate.value
  }

  axios.post("http://localhost:8080/updatestu", obj)
    .then(res => {
      if (res.data > 0) {
        ElMessage.success("修改成功!")
        updialogVisible.value = false
        search()
      }else{
		ElMessage.error("修改失败！")
	  }
    })
}

// 添加学生
function addstu() {
  let obj = {
    id: "",
    stuname: stuname.value,
    classnum: classnum.value,
    stunum: stunum.value,
    path: "null",
    homeworkstate: "0"
  }

  axios.post("http://localhost:8080/addstu", obj)
    .then(res => {
      if (res.data > 0) {
        ElMessage.success("添加成功!")
        dialogVisible.value = false
        //清空表单
        stuname.value = ""
        classnum.value = ""
        stunum.value = ""
        search()
      }
    })
}

// 搜索方法
const handleSearch = () => {
  currentPage.value = 1
  search()
}

// 重置搜索
const resetSearch = () => {
  searchForm.value = {
    stuname: '',
    classnum: '',
    stunum: ''
  }
  handleSearch()
}

// 查询数据
const search = () => {
  axios.get("http://localhost:8080/getstu", {
    params: searchForm.value
  })
  .then(res => {
    tableData.value = res.data
  })
}

// 查询学生
// function search() {
//   axios.get("http://localhost:8080/getstu")
//     .then(res => {
//       tableData.value = res.data
//       // 重置到第一页
//       currentPage.value = 1
//     })
// }

// 删除学生
function delstu(num) {
  // 确认对话框
  ElMessageBox.confirm('确定要删除该学生信息吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    axios.get("http://localhost:8080/deletestu?stunum=" + num)
      .then(res => {
        if (res.data > 0) {
          ElMessage.success("删除成功!")
          search()
        } else {
          ElMessage.error("删除失败!")
        }
      })
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

// 分页大小改变
function handleSizeChange(val) {
  pageSize.value = val
}

// 当前页改变
function handleCurrentChange(val) {
  currentPage.value = val
}
</script>

<style scoped>
.student-management {
  padding: 20px;
}

.search-form {
  margin-bottom: 20px;
  padding: 20px;
  background: #f5f7fa;
  border-radius: 4px;
}

.operation-buttons {
  margin-bottom: 10px;
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

.el-input {
  margin-bottom: 5px;
}
</style>