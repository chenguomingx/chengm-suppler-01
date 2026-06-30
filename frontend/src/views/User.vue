<template>
  <div class="page-container">
    <div class="page-header">
      <div class="page-title">用户管理</div>
      <el-button type="primary" :icon="Plus" @click="handleAdd">新增用户</el-button>
    </div>

    <div class="search-bar">
      <el-input
        v-model="keyword"
        placeholder="搜索用户名/姓名/手机号"
        clearable
        style="width: 300px"
        @keyup.enter="fetchList"
      />
      <el-button type="primary" :icon="Search" @click="fetchList">搜索</el-button>
      <el-button :icon="Refresh" @click="handleReset">重置</el-button>
    </div>

    <div class="table-container">
      <el-table :data="tableData" v-loading="loading" stripe border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" align="center" />
        <el-table-column prop="username" label="用户名" width="150" />
        <el-table-column prop="name" label="姓名" width="120" />
        <el-table-column prop="phone" label="手机号" width="150" />
        <el-table-column prop="email" label="邮箱" width="200" />
        <el-table-column prop="role" label="角色" width="120" align="center">
          <template #default="{ row }">
            <el-tag :type="row.role === 'admin' ? 'danger' : 'primary'">
              {{ row.role === 'admin' ? '管理员' : '普通用户' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="100" align="center">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">
              {{ row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="220" align="center" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link :icon="Edit" @click="handleEdit(row)">编辑</el-button>
            <el-button
              type="warning"
              size="small"
              v-if="row.status === 1"
              @click="handleToggleStatus(row, 0)"
            >禁用</el-button>
            <el-button
              type="success"
              size="small"
              v-else
              @click="handleToggleStatus(row, 1)"
            >启用</el-button>
            <el-button type="danger" size="small" :icon="Delete" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
          v-model:current-page="pageNum"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          background
          @size-change="handleSizeChange"
          @current-change="handlePageChange"
        />
      </div>
    </div>

    <el-alert
      title="功能开发中"
      type="info"
      :closable="false"
      show-icon
      style="margin-top: 20px"
    >
      <template #default>
        用户管理模块功能正在开发中，当前为演示数据。
      </template>
    </el-alert>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Search, Refresh, Edit, Delete } from '@element-plus/icons-vue'

const loading = ref(false)
const keyword = ref('')
const tableData = ref([])
const total = ref(0)
const pageNum = ref(1)
const pageSize = ref(10)

const mockData = [
  { id: 1, username: 'admin', name: '系统管理员', phone: '13800138001', email: 'admin@example.com', role: 'admin', status: 1, createTime: '2024-01-01 10:00:00' },
  { id: 2, username: 'zhangsan', name: '张三', phone: '13800138002', email: 'zhangsan@example.com', role: 'user', status: 1, createTime: '2024-01-15 14:30:00' },
  { id: 3, username: 'lisi', name: '李四', phone: '13800138003', email: 'lisi@example.com', role: 'user', status: 1, createTime: '2024-02-10 09:20:00' },
  { id: 4, username: 'wangwu', name: '王五', phone: '13800138004', email: 'wangwu@example.com', role: 'user', status: 0, createTime: '2024-02-20 16:45:00' },
  { id: 5, username: 'zhaoliu', name: '赵六', phone: '13800138005', email: 'zhaoliu@example.com', role: 'user', status: 1, createTime: '2024-03-05 11:10:00' }
]

const fetchList = async () => {
  loading.value = true
  try {
    await new Promise(resolve => setTimeout(resolve, 300))
    let data = [...mockData]
    if (keyword.value) {
      const kw = keyword.value.toLowerCase()
      data = data.filter(item =>
        item.username.toLowerCase().includes(kw) ||
        item.name.toLowerCase().includes(kw) ||
        item.phone.includes(kw)
      )
    }
    total.value = data.length
    const start = (pageNum.value - 1) * pageSize.value
    tableData.value = data.slice(start, start + pageSize.value)
  } finally {
    loading.value = false
  }
}

const handleReset = () => {
  keyword.value = ''
  pageNum.value = 1
  fetchList()
}

const handlePageChange = (val) => {
  pageNum.value = val
  fetchList()
}

const handleSizeChange = (val) => {
  pageSize.value = val
  pageNum.value = 1
  fetchList()
}

const handleAdd = () => {
  ElMessage.info('新增用户功能开发中')
}

const handleEdit = (row) => {
  ElMessage.info('编辑用户功能开发中')
}

const handleToggleStatus = (row, status) => {
  const action = status === 1 ? '启用' : '禁用'
  ElMessageBox.confirm(
    `确定要${action}用户"${row.name}"吗？`,
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    row.status = status
    ElMessage.success(`${action}成功`)
  }).catch(() => {})
}

const handleDelete = (row) => {
  ElMessageBox.confirm(
    `确定要删除用户"${row.name}"吗？`,
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    ElMessage.success('删除成功')
    fetchList()
  }).catch(() => {})
}

onMounted(() => {
  fetchList()
})
</script>
