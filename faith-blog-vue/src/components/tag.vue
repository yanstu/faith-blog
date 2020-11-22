<template>
	<div class="tag">
		<el-card class="box-card">
			<div slot="header" class="d-flex align-items-center">
				<img class="card-icon" src="../assets/images/biaoqian.png" />
				<span>{{$t('tag.tag')}}</span>
			</div>
			<div class="text item">
				<el-tag size="small" class="tag-item" @click="tag('Java')">Java[12]</el-tag>
				<el-tag size="small" class="tag-item" type="success" @click="tag('SpringBoot')">SpringBoot[8]</el-tag>
				<el-tag size="small" class="tag-item" type="info" @click="tag('HTML')">HTML[8]</el-tag>
				<el-tag size="small" class="tag-item" type="warning" @click="tag('Mysql')">Mysql[5]</el-tag>
				<el-tag size="small" class="tag-item" type="danger" @click="tag('Vue')">Vue[3]</el-tag>
				<el-tag size="small" class="tag-item" type="info" @click="tag('jQuery')">jQuery[6]</el-tag>
				<el-tag size="small	" class="tag-item" type="success" @click="tag('SpringCloud')">SpringCloud[9]</el-tag>
			</div>
		</el-card>
	</div>
</template>

<script>
	export default {
		name: 'tag',
		methods: {
			tag(name) {
				this.$router.push({
					name: 'tag',
					params: {
						'name': name
					}
				});
			}
		}
	}
</script>

<style scoped>
	.box-card .item:hover {
		color: #409EFF;
		cursor: pointer;
	}
	
	.box-card span {
		font-weight: bold;
	}
	
	.card-icon {
		width: 20px;
		height: 20px;
		margin-right: 10px;
	}
	
	.tag-item {
		margin: 3px 10px;
	}
</style>