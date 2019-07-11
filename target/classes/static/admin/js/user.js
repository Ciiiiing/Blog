var app = new Vue({
    el: '#app',
    data: {
        user: {},
        pass: {
            id: '',
            password: '',
            repassword: '',
        },
        localUpload: api.user.localUpload,
        avatarDialog: false,
        avatarList: [],
        defaultActive: '9',
        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标志
    },
    created() {
        window.onload = function () {
            app.changeDiv();
        }
        window.onresize = function () {
            app.changeDiv();
        }
        this.getUserInfo();
    },
    mounted() {
        this.$refs.loader.style.display = 'none';
    },
    methods: {
        _notify(message, type) {
            this.$message({
                message: message,
                type: type
            })
        },
        //获取当前用户信息
        getUserInfo() {
            this.$http.get(api.user.info).then(result => {
                this.user = result.body.data;
                this.pass.id = result.body.data.id;
            });
        },

        save() {
            this.$http.post(api.user.update, JSON.stringify({id:this.user.id,username:this.user.username,avater:this.user.avatar,introduce:this.user.introduce,remark:this.user.remark})).then(result => {
                if (result.body.code == 200) {
                    this._notify(result.body.msg, 'success');
                    //window.location.href = api.common.logout;
                    //window.location.href = "/login";
                } else {
                    this._notify(result.body.msg, 'error')
                }
            });
        },
        //触发关闭按钮
        handleClose() {
            this.avatarDialog = false;
        },
        handleEditAvatar() {
            this.$http.get(api.user.avatar).then(response => {
                this.avatarList = response.body;
            });
            this.avatarDialog = true;
        },
        //修改头像
        changeAvatar(url) {
            this.user.avatar = url;
            var data = {
                id: this.user.id,
                avatar: this.user.avatar
            };
            this.$http.post(api.user.update, JSON.stringify(data)).then(response => {
                this.avatarDialog = false;
                if (response.body.code == 200) {
                    this._notify('更换头像成功', 'success')
                } else {
                    this._notify(response.body.msg, 'error')
                }
            })
        },

        changePassword() {
            if (this.pass.password.length < 6) {
                this._notify('请重新输入密码，密码长度在6位及以上', 'warning');
            } else if (this.pass.password != this.pass.repassword) {
                this._notify('两次输入的密码不一致', 'warning');
            } else {
                this.$http.post(api.user.update, JSON.stringify(this.pass)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success');
                        //执行/logout请求
                        this.logout();
                    } else {
                        this._notify(result.body.msg, 'error');
                    }
                    this.clearPass();
                });
            }
        },
        clearPass() {
            this.pass.repassword = '';
            this.pass.password = '';
        },

        /**
         * 监听窗口改变UI样式（区别PC和Phone）
         */
        changeDiv() {
            let isMobile = this.isMobile();
            if (isMobile) {
                //手机访问
                this.sidebarFlag = ' hideSidebar mobile ';
                this.sidebarStatus = false;
                this.mobileStatus = true;
            } else {
                this.sidebarFlag = ' openSidebar';
                this.sidebarStatus = true;
                this.mobileStatus = false;
            }
        },
        isMobile() {
            let rect = body.getBoundingClientRect();
            return rect.width - RATIO < WIDTH
        },
        handleSidebar() {
            if (this.sidebarStatus) {
                this.sidebarFlag = ' hideSidebar ';
                this.sidebarStatus = false;

            } else {
                this.sidebarFlag = ' openSidebar ';
                this.sidebarStatus = true;
            }
            let isMobile = this.isMobile();
            if (isMobile) {
                this.sidebarFlag += ' mobile ';
                this.mobileStatus = true;
            }
        },
        //蒙版
        drawerClick() {
            this.sidebarStatus = false;
            this.sidebarFlag = ' hideSidebar mobile '
        },
        logout() {
            this.$http.get(api.common.logout).then(result => {
                if (result.body.code == 200) {
                window.location.href = "/login";
            }
        });
        }
    },
});
