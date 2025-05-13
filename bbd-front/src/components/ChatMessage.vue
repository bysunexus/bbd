<script setup lang="ts">
interface Props {
  data: any;
  dataType: 'text' | 'ledger' | 'stat' | 'tip';
  role: 'Me' | '叨';
}

defineProps<Props>();

const emit = defineEmits<{
  (e: 'tipClick', tip: string): void
}>();

const handleTipClick = (tip: string) => {
  emit('tipClick', tip);
};
</script>

<template>
  <el-card class="card">
    <el-avatar size="small" shape="square">{{ role }}</el-avatar>
    <div v-if="dataType === 'text'">
      <p>
        <el-text>{{ data.content }}</el-text>
      </p>
    </div>
    <div v-else-if="dataType === 'ledger'">
      <el-descriptions
        class="margin-top"
        title="已记录花费"
        :column="3"
        border
      >
        <template #extra>
          <el-button type="primary">搞错了，我要删除</el-button>
        </template>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              时间
            </div>
          </template>
          {{ data.feeDate }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              金额
            </div>
          </template>
          {{ data.amount }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              分类
            </div>
          </template>
          {{ data.feeTypeName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              描述
            </div>
          </template>
          {{ data.desc }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <div v-else-if="dataType === 'stat'">

    </div>
    <div v-else-if="dataType === 'tip'">
      <p>
        <el-text>{{ data.content }}</el-text>
      </p>
      <div class="flex gap">
        <el-tag type="primary" v-for="tip in data.tips" @click="handleTipClick(tip)">{{ tip }}</el-tag>
      </div>
    </div>
  </el-card>
</template>
<style scoped>
.card {
  width: 90%;
  max-width: 90%;
  margin-bottom: 1rem;
}

.gap {
  gap: 0.5rem;
}

.flex {
  display: flex;
}

.cell-item {
  display: flex;
  align-items: center;
}
</style>
