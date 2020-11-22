<template>
	<div class="archive">
		<div class="count">{{this.$route.params.name || $t('header.archive')}}：234{{$t('archive.article')}}</div>
		<el-timeline>
			<el-timeline-item v-for="(activity, index) in activities" :key="index" :color="activity.color" :timestamp="activity.timestamp" placement="top" @mouseenter="hoverLine(activity)">
				<div class="line-item">
					<router-link to="/article" tag="span">{{activity.content}}</router-link>
				</div>
			</el-timeline-item>
		</el-timeline>
	</div>
</template>

<script>
	export default {
		name: 'archive',
		data() {
			return {
				activities: [{
					content: 'springBoot整合Redis',
					timestamp: '2018-04-15'
				}, {
					content: 'Activiti工作流',
					timestamp: '2018-04-13'
				}, {
					content: 'Vue路由',
					timestamp: '2018-04-11'
				}]
			};
		},
		methods: {
			hoverLine(activity) {
				activity.color = "#409eff"
			}
		}
	}
</script>

<style scoped>
	.line-item {
		display: inline-block;
	}
	
	.line-item:hover {
		cursor: pointer;
		color: #409EFF;
	}
	
	.count {
		margin-bottom: 20px;
		font-size: 20px;
		color: #E6A23C;
	}
</style>