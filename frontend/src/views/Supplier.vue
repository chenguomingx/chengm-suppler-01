<template>
  <div class="page-container">
    <div class="page-header">
      <div class="page-title">供应商管理</div>
      <el-button type="primary" :icon="Plus" @click="handleAdd">新增供应商</el-button>
    </div>

    <div class="search-bar">
      <el-input
        v-model="keyword"
        placeholder="搜索供应商名称/联系人/电话"
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
        <el-table-column prop="supplierName" label="供应商名称" min-width="180" />
        <el-table-column prop="contactPerson" label="联系人" width="120" />
        <el-table-column prop="contactPhone" label="联系电话" width="150" />
        <el-table-column prop="contactEmail" label="邮箱" min-width="180" />
        <el-table-column prop="address" label="地址" min-width="200" show-overflow-tooltip />
        <el-table-column prop="status" label="状态" width="100" align="center">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">
              {{ row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="180" align="center" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link :icon="Edit" @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" link :icon="Delete" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="600px"
      :close-on-click-modal="false"
      @closed="handleDialogClosed"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="供应商名称" prop="supplierName">
          <el-input v-model="form.supplierName" placeholder="请输入供应商名称" maxlength="100" />
        </el-form-item>
        <el-form-item label="联系人" prop="contactPerson">
          <el-input v-model="form.contactPerson" placeholder="请输入联系人" maxlength="50" />
        </el-form-item>
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系电话" maxlength="30" />
        </el-form-item>
        <el-form-item label="联系邮箱" prop="contactEmail">
          <el-input v-model="form.contactEmail" placeholder="请输入联系邮箱" maxlength="100" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" maxlength="255" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            :rows="3"
            placeholder="请输入备注"
            maxlength="500"
            show-word-limit
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" :loading="submitLoading" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Search, Refresh, Edit, Delete } from '@element-plus/icons-vue'
import {
  getSupplierList,
  addSupplier,
  updateSupplier,
  deleteSupplier
} from '@/api/supplier'

const loading = ref(false)
const submitLoading = ref(false)
const keyword = ref('')
const tableData = ref([])
const dialogVisible = ref(false)
const dialogTitle = ref('新增供应商')
const formRef = ref(null)
const isEdit = ref(false)

const defaultForm = {
  id: null,
  supplierName: '',
  contactPerson: '',
  contactPhone: '',
  contactEmail: '',
  address: '',
  remark: '',
  status: 1
}

const form = reactive({ ...defaultForm })

const rules = {
  supplierName: [
    { required: true, message: '请输入供应商名称', trigger: 'blur' }
  ]
}

const fetchList = async () => {
  loading.value = true
  try {
    const res = await getSupplierList(keyword.value)
    tableData.value = res.data || []
  } finally {
    loading.value = false
  }
}

const handleReset = () => {
  keyword.value = ''
  fetchList()
}

const handleAdd = () => {
  isEdit.value = false
  dialogTitle.value = '新增供应商'
  Object.assign(form, defaultForm)
  dialogVisible.value = true
}

const handleEdit = (row) => {
  isEdit.value = true
  dialogTitle.value = '编辑供应商'
  Object.assign(form, defaultForm, row)
  dialogVisible.value = true
}

const handleDialogClosed = () => {
  formRef.value && formRef.value.resetFields()
  Object.assign(form, defaultForm)
}

const handleSubmit = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (!valid) return
    submitLoading.value = true
    try {
      if (isEdit.value) {
        await updateSupplier(form)
        ElMessage.success('修改成功')
      } else {
        await addSupplier(form)
        ElMessage.success('新增成功')
      }
      dialogVisible.value = false
      fetchList()
    } finally {
      submitLoading.value = false
    }
  })
}

const handleDelete = (row) => {
  ElMessageBox.confirm(
    `确定要删除供应商"${row.supplierName}"吗？`,
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      await deleteSupplier(row.id)
      ElMessage.success('删除成功')
      fetchList()
    } catch {}
  }).catch(() => {})
}

onMounted(() => {
  fetchList()
})
</script>
