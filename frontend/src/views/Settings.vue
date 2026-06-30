<template>
  <div class="page-container">
    <div class="page-header">
      <div class="page-title">系统配置</div>
    </div>

    <el-card>
      <el-tabs v-model="activeTab">
        <el-tab-pane label="基本设置" name="basic">
          <el-form label-width="120px" style="max-width: 600px">
            <el-form-item label="系统名称">
              <el-input v-model="form.systemName" placeholder="请输入系统名称" />
            </el-form-item>
            <el-form-item label="系统Logo">
              <el-upload
                action="#"
                :show-file-list="false"
                :auto-upload="false"
              >
                <el-button type="primary">上传Logo</el-button>
              </el-upload>
            </el-form-item>
            <el-form-item label="系统描述">
              <el-input
                v-model="form.description"
                type="textarea"
                :rows="3"
                placeholder="请输入系统描述"
              />
            </el-form-item>
            <el-form-item label="开启注册">
              <el-switch v-model="form.enableRegister" />
            </el-form-item>
            <el-form-item label="默认角色">
              <el-select v-model="form.defaultRole" placeholder="请选择默认角色">
                <el-option label="管理员" value="admin" />
                <el-option label="普通用户" value="user" />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleSave">保存配置</el-button>
              <el-button @click="handleReset">重置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="安全设置" name="security">
          <el-form label-width="120px" style="max-width: 600px">
            <el-form-item label="密码最小长度">
              <el-input-number v-model="form.minPasswordLength" :min="6" :max="20" />
            </el-form-item>
            <el-form-item label="密码复杂度">
              <el-checkbox-group v-model="form.passwordComplexity">
                <el-checkbox label="number">必须包含数字</el-checkbox>
                <el-checkbox label="letter">必须包含字母</el-checkbox>
                <el-checkbox label="special">必须包含特殊字符</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="登录失败锁定">
              <el-input-number v-model="form.maxLoginAttempts" :min="3" :max="10" />
              <span style="margin-left: 10px; color: #909399">次后锁定</span>
            </el-form-item>
            <el-form-item label="会话超时">
              <el-input-number v-model="form.sessionTimeout" :min="30" :max="1440" />
              <span style="margin-left: 10px; color: #909399">分钟</span>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleSave">保存配置</el-button>
              <el-button @click="handleReset">重置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="邮件设置" name="email">
          <el-form label-width="120px" style="max-width: 600px">
            <el-form-item label="SMTP服务器">
              <el-input v-model="form.smtpHost" placeholder="smtp.example.com" />
            </el-form-item>
            <el-form-item label="SMTP端口">
              <el-input-number v-model="form.smtpPort" :min="1" :max="65535" />
            </el-form-item>
            <el-form-item label="发件人邮箱">
              <el-input v-model="form.senderEmail" placeholder="noreply@example.com" />
            </el-form-item>
            <el-form-item label="发件人密码">
              <el-input v-model="form.senderPassword" type="password" placeholder="请输入授权码" show-password />
            </el-form-item>
            <el-form-item label="开启SSL">
              <el-switch v-model="form.enableSSL" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleSave">保存配置</el-button>
              <el-button @click="handleTest">测试连接</el-button>
              <el-button @click="handleReset">重置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <el-alert
      title="功能开发中"
      type="info"
      :closable="false"
      show-icon
      style="margin-top: 20px"
    >
      <template #default>
        系统配置功能正在开发中，当前为演示界面，配置不会实际保存。
      </template>
    </el-alert>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'

const activeTab = ref('basic')

const defaultForm = {
  systemName: '供应商管理系统',
  description: '一套完整的供应商管理解决方案',
  enableRegister: false,
  defaultRole: 'user',
  minPasswordLength: 8,
  passwordComplexity: ['number', 'letter'],
  maxLoginAttempts: 5,
  sessionTimeout: 120,
  smtpHost: 'smtp.example.com',
  smtpPort: 465,
  senderEmail: 'noreply@example.com',
  senderPassword: '',
  enableSSL: true
}

const form = reactive({ ...defaultForm })

const handleSave = () => {
  ElMessage.success('配置保存成功')
}

const handleReset = () => {
  Object.assign(form, defaultForm)
  ElMessage.info('已重置为默认配置')
}

const handleTest = () => {
  ElMessage.success('邮件服务器连接成功')
}
</script>
