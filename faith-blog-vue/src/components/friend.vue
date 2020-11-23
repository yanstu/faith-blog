<template>
	<div class="friend">
		<el-card class="box-card">
		  <div slot="header" class="d-flex align-items-center">
		  	<img class="card-icon" src="../assets/images/lianjie.png"/>
		    <span>{{$t('friend.friend')}}</span>
		    <el-button class="ml-auto" style="padding: 3px 0" type="text">
		    	<router-link to="/apply" tag="span">{{$t('friend.exchange')}}</router-link>
		    </el-button>
		  </div>
		  <div v-for="link in links" :key="link" class="text item"><a v-text="link.sitename" :href="link.url">某某的博客</a></div>
		</el-card>
	</div>
</template>

<script>
/**
 * 友情链接组件
 * 接口地址：
 *       - 获取全部友情链接 http://127.0.0.1:8080/link/all
 */

export default {
  name: 'friend',
  data() {
	  return {
		  links:{}
	  }
  },
  created () {
	  this.$axios.get('/link/all')
	  .then(res => {
		  this.links = res.data.data;
	  })
	  .catch(err => {
		  console.error(err); 
	  })
  }
}
</script>

<style scoped>
.box-card .item a{
	text-decoration: none;
	/*color: #F56C6C;*/
}
.box-card span{
	font-weight: bold;
}
.card-icon{
	width: 20px;
	height: 20px;
	margin-right: 10px;
}
</style>