<template>
  <div class="login-container" align="center">
    <div style="width: 50%">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
        <label style="font-size: 50px;font-family: 隶书">用户注册</label>
        <el-form-item prop="r_name">
          <el-input v-model="ruleForm.r_name" placeholder="用户名" clearable ></el-input>
        </el-form-item>
        <el-form-item prop="r_pwd">
          <el-input v-model="ruleForm.r_pwd" placeholder="密码" show-password clearable ></el-input>
        </el-form-item>
        <el-form-item prop="r_phone">
          <el-input type="text" v-model="ruleForm.r_phone" placeholder="联系电话" maxlength="11" show-word-limit clearable></el-input>
        </el-form-item>
        <el-form-item prop="r_email">
          <el-input v-model="ruleForm.r_email" placeholder="邮箱" clearable></el-input>
        </el-form-item>
        <el-form-item prop="r_city">
          <el-input v-model="ruleForm.r_city" placeholder="居住城市" clearable></el-input>
        </el-form-item>
        <el-form-item prop="r_gender">
          <el-radio-group v-model="ruleForm.r_gender" size="large">
            <el-radio border label="1" >男生</el-radio>
            <el-radio border label="0" >女生</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="r_bank">
          <el-input v-model="ruleForm.r_bank" placeholder="银行账号" maxlength="16" minlength="16" show-word-limit clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="btn_RegistForm('ruleForm')" round>注册</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="info" style="width: 100%" @click=" btn_clear('ruleForm')" round>取消</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-dialog title="验证码" :visible.sync="dialogFormVisible"  style="width: 30%;position: fixed;left: 35%">
        <div>
          <el-image style="width: 200px;height: 100px" :src="this.str + this.chargepng[this.randomnumber] + this.str2" ></el-image>
        </div>
        <el-form :model="chargeForm">
          <el-form-item label="验证码:" :label-width="120">
            <el-input v-model="chargeForm.chargeinfo"  autocomplete="off" style="width: 200px"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="chargeFunc">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  export default {
    data () {

      return {
        ruleForm: {
          r_name: '',
          r_pwd: '',
          r_phone: '',
          r_email: '',
          r_city: '',
          r_gender: '',
          r_bank: '',
          name:''
        },
        str:"/static/photo/chargepng/",
        str2:".png",
        registok:"0",
        chargeForm:{
          chargeinfo:''
        },
        dialogFormVisible:false,
        chargepng:[
          'ENZK',
          'NLME',
          'OFdE'
        ],
        randomnumber:0,
        rules: {
          r_name: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
          ],
          r_pwd: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ],
          r_phone: [
            {required: true, message: '请输入电话号码', trigger: 'blur'},
            { min: 11, max: 11, message: '请输入11位手机号', trigger: ['blur', 'change']},

          ],
          r_email: [
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] },
          ],
          r_city: [
            {required: true, message: '请输入居住地', trigger: 'blur'}
          ],
          r_bank: [
            {required: true, message: '请输入银行账户', trigger: 'blur'},
            { min: 16, max: 16, message: '请输入16位银行账户', trigger: ['blur', 'change']},

          ],
          r_gender:  [
            { required: true, message: '请选择您的性别', trigger: 'change' }
          ],
        }
      }
    },
    methods: {
      chargeFunc(){
        if(this.chargeForm.chargeinfo == this.chargepng[this.randomnumber]){
          this.$message({
            message: '验证码正确',
            type: 'success'
          });
          this.dialogFormVisible = false
          this.$axios.post('http://localhost:8080/users/insertUser',{
            userName: this.ruleForm.r_name,
            userPwd: this.ruleForm.r_pwd,
            userPhone: this.ruleForm.r_phone,
            userEmail: this.ruleForm.r_email,
            userCity: this.ruleForm.r_city,
            userGender: this.ruleForm.r_gender,
            userBank: this.ruleForm.r_bank
          }).then( res =>{
            this.$msgbox({
              message:"注册成功,跳转登陆界面",
              type:"success"
            }).then(()=>{
              this.$router.replace({name:'userlogin'})
            })
          })
        }else{
          this.$message({
            message: '验证码错误',
            type: 'error'
          });

        }
      },
        btn_RegistForm (formName) {
        this.randomnumber = Math.round(Math.random()*2)
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!')
            console.log(this.ruleForm.name + '   ' + this.ruleForm.pwd)
            this.$axios.post('http://localhost:8080/users/findUser',{
              userName: this.ruleForm.r_name
            }).then(res=>{
              console.log(res)
              if(res.data != 2){
                this.$msgbox({
                  message:'用户名重复,请修改!',
                  type:'warning'
                })
              }else{
                this.dialogFormVisible = true
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      btn_clear (formName){
        this.$refs[formName].resetFields();
      }
    }
  }
</script>
<style>
  .login-container {
    margin-top: 5%;
    margin-left: 33%;
    margin-right: 33%;
    border:3px solid #4a88c7;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    background-color: #42b983;
  }
</style>
