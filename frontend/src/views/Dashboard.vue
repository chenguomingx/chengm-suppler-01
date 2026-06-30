<template>
  <div class="page-container">
    <div class="page-header">
      <div class="page-title">数据概览</div>
    </div>

    <div class="dashboard-cards">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-card shadow="hover" class="stat-card">
            <div class="stat-content">
              <div class="stat-icon blue">
                <el-icon><OfficeBuilding /></el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-value">10</div>
                <div class="stat-label">供应商总数</div>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card shadow="hover" class="stat-card">
            <div class="stat-content">
              <div class="stat-icon green">
                <el-icon><User /></el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-value">5</div>
                <div class="stat-label">用户总数</div>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card shadow="hover" class="stat-card">
            <div class="stat-content">
              <div class="stat-icon orange">
                <el-icon><CircleCheck /></el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-value">9</div>
                <div class="stat-label">启用供应商</div>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card shadow="hover" class="stat-card">
            <div class="stat-content">
              <div class="stat-icon red">
                <el-icon><CircleClose /></el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-value">1</div>
                <div class="stat-label">禁用供应商</div>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <el-alert
      title="功能开发中"
      type="info"
      :closable="false"
      show-icon
      style="margin-top: 20px"
    >
      <template #default>
        数据可视化图表功能正在开发中，当前为演示数据。
      </template>
    </el-alert>
  </div>
</template>

<script setup>
import { OfficeBuilding, User, CircleCheck, CircleClose } from '@element-plus/icons-vue'
</script>

<style lang="scss" scoped>
.dashboard-cards {
  margin-bottom: 20px;
}

.stat-card {
  .stat-content {
    display: flex;
    align-items: center;
    gap: 20px;
  }

  .stat-icon {
    width: 60px;
    height: 60px;
    border-radius: 12px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 30px;
    color: #fff;

    &.blue {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    }

    &.green {
      background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);
    }

    &.orange {
      background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
    }

    &.red {
      background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);
    }
  }

  .stat-info {
    .stat-value {
      font-size: 28px;
      font-weight: 600;
      color: #303133;
      line-height: 1.2;
    }

    .stat-label {
      font-size: 14px;
      color: #909399;
      margin-top: 4px;
    }
  }
}
</style>
