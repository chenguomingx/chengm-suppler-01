<template>
  <div class="page-container">
    <div class="page-header">
      <div class="page-title">供应商管理</div>
      <el-button type="primary" :icon="Plus" @click="handleAdd">新增供应商</el-button>
    </div>

    <div class="search-bar">
      <el-input
        v-model="keyword"
        placeholder="搜索公司名称/联系人/电话/统一社会信用代码/法定代表人"
        clearable
        style="width: 400px"
        @keyup.enter="fetchList"
      />
      <el-button type="primary" :icon="Search" @click="fetchList">搜索</el-button>
      <el-button :icon="Refresh" @click="handleReset">重置</el-button>
    </div>

    <div class="table-container">
      <el-table :data="tableData" v-loading="loading" stripe border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" align="center" />
        <el-table-column prop="supplierName" label="公司名称" min-width="200" show-overflow-tooltip />
        <el-table-column prop="unifiedSocialCreditCode" label="统一社会信用代码" width="200" show-overflow-tooltip />
        <el-table-column prop="legalRepresentative" label="法定代表人" width="120" />
        <el-table-column label="注册资本" width="130" align="right">
          <template #default="{ row }">
            {{ row.registeredCapital ? row.registeredCapital + ' 万' : '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="establishmentDate" label="成立日期" width="120" />
        <el-table-column prop="industry" label="所属行业" width="180" show-overflow-tooltip />
        <el-table-column prop="province" label="省份" width="100" />
        <el-table-column prop="businessStatus" label="经营状态" width="100" align="center">
          <template #default="{ row }">
            <el-tag v-if="row.businessStatus" size="small" :type="row.businessStatus === '存续' ? 'success' : 'info'">
              {{ row.businessStatus }}
            </el-tag>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column prop="contactPerson" label="联系人" width="100" />
        <el-table-column prop="contactPhone" label="联系电话" width="140" />
        <el-table-column prop="status" label="状态" width="100" align="center">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">
              {{ row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="260" align="center" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link :icon="View" @click="handleView(row)">详情</el-button>
            <el-button type="success" link v-if="row.status === 1" @click="handleToggleStatus(row, 0)">禁用</el-button>
            <el-button type="warning" link v-else @click="handleToggleStatus(row, 1)">启用</el-button>
            <el-button type="primary" link :icon="Edit" @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" link :icon="Delete" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="900px"
      :close-on-click-modal="false"
      @closed="handleDialogClosed"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="120px"
      >
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="公司名称" prop="supplierName">
              <el-input v-model="form.supplierName" placeholder="请输入公司名称" maxlength="200" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="统一社会信用代码" prop="unifiedSocialCreditCode">
              <el-input v-model="form.unifiedSocialCreditCode" placeholder="请输入统一社会信用代码" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="法定代表人" prop="legalRepresentative">
              <el-input v-model="form.legalRepresentative" placeholder="请输入法定代表人" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="注册资本(万)" prop="registeredCapital">
              <el-input-number v-model="form.registeredCapital" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="成立日期" prop="establishmentDate">
              <el-date-picker v-model="form.establishmentDate" type="date" placeholder="请选择成立日期" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="经营状态" prop="businessStatus">
              <el-select v-model="form.businessStatus" placeholder="请选择经营状态" style="width: 100%">
                <el-option label="存续" value="存续" />
                <el-option label="在业" value="在业" />
                <el-option label="吊销" value="吊销" />
                <el-option label="注销" value="注销" />
                <el-option label="迁入" value="迁入" />
                <el-option label="迁出" value="迁出" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="所属省份" prop="province">
              <el-input v-model="form.province" placeholder="请输入省份" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属城市" prop="city">
              <el-input v-model="form.city" placeholder="请输入城市" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属区县" prop="district">
              <el-input v-model="form.district" placeholder="请输入区县" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="工商注册号" prop="businessRegistrationNumber">
              <el-input v-model="form.businessRegistrationNumber" placeholder="请输入工商注册号" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="组织机构代码" prop="organizationCode">
              <el-input v-model="form.organizationCode" placeholder="请输入组织机构代码" maxlength="20" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="纳税人识别号" prop="taxpayerIdentificationNumber">
              <el-input v-model="form.taxpayerIdentificationNumber" placeholder="请输入纳税人识别号" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="登记机关" prop="registrationAuthority">
              <el-input v-model="form.registrationAuthority" placeholder="请输入登记机关" maxlength="100" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="核准日期" prop="approvalDate">
              <el-date-picker v-model="form.approvalDate" type="date" placeholder="请选择核准日期" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="参保人数" prop="insuranceCount">
              <el-input-number v-model="form.insuranceCount" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="营业期限开始" prop="businessTermStart">
              <el-date-picker v-model="form.businessTermStart" type="date" placeholder="请选择开始日期" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="营业期限结束" prop="businessTermEnd">
              <el-date-picker v-model="form.businessTermEnd" type="date" placeholder="请选择结束日期" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="企业类型" prop="enterpriseType">
              <el-input v-model="form.enterpriseType" placeholder="请输入企业类型" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属行业" prop="industry">
              <el-input v-model="form.industry" placeholder="请输入所属行业" maxlength="100" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="曾用名" prop="formerName">
          <el-input v-model="form.formerName" placeholder="请输入曾用名" maxlength="200" />
        </el-form-item>
        <el-form-item label="经营范围" prop="businessScope">
          <el-input
            v-model="form.businessScope"
            type="textarea"
            :rows="3"
            placeholder="请输入经营范围"
            maxlength="2000"
            show-word-limit
          />
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系人" prop="contactPerson">
              <el-input v-model="form.contactPerson" placeholder="请输入联系人" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="contactPhone">
              <el-input v-model="form.contactPhone" placeholder="请输入联系电话" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系邮箱" prop="contactEmail">
              <el-input v-model="form.contactEmail" placeholder="请输入联系邮箱" maxlength="100" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio :label="1">启用</el-radio>
                <el-radio :label="0">禁用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" maxlength="255" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            :rows="2"
            placeholder="请输入备注"
            maxlength="500"
            show-word-limit
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button v-if="!isView" type="primary" :loading="submitLoading" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Search, Refresh, Edit, Delete, View } from '@element-plus/icons-vue'
import {
  getSupplierList,
  addSupplier,
  updateSupplier,
  deleteSupplier,
  updateSupplierStatus
} from '@/api/supplier'

const loading = ref(false)
const submitLoading = ref(false)
const keyword = ref('')
const tableData = ref([])
const dialogVisible = ref(false)
const dialogTitle = ref('新增供应商')
const formRef = ref(null)
const isEdit = ref(false)
const isView = ref(false)

const defaultForm = {
  id: null,
  supplierName: '',
  unifiedSocialCreditCode: '',
  legalRepresentative: '',
  registeredCapital: null,
  establishmentDate: null,
  businessStatus: '',
  province: '',
  city: '',
  district: '',
  businessRegistrationNumber: '',
  organizationCode: '',
  taxpayerIdentificationNumber: '',
  registrationAuthority: '',
  approvalDate: null,
  businessTermStart: null,
  businessTermEnd: null,
  enterpriseType: '',
  industry: '',
  businessScope: '',
  formerName: '',
  insuranceCount: null,
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
    { required: true, message: '请输入公司名称', trigger: 'blur' }
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
  isView.value = false
  dialogTitle.value = '新增供应商'
  Object.assign(form, defaultForm)
  dialogVisible.value = true
}

const handleView = (row) => {
  isEdit.value = false
  isView.value = true
  dialogTitle.value = '供应商详情'
  Object.assign(form, defaultForm, row)
  dialogVisible.value = true
}

const handleEdit = (row) => {
  isEdit.value = true
  isView.value = false
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

const handleToggleStatus = (row, status) => {
  const action = status === 1 ? '启用' : '禁用'
  ElMessageBox.confirm(
    `确定要${action}供应商"${row.supplierName}"吗？`,
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      await updateSupplierStatus(row.id, status)
      ElMessage.success(`${action}成功`)
      fetchList()
    } catch {}
  }).catch(() => {})
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
