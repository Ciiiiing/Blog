var app = new Vue({
    el: '#app',
    data: {
        setting: {},
        defaultActive: '10',
        localUpload: api.user.localUpload,
        avatarDialog: false,
        avatarList: [],
        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标志
    },
    created() {
        window.onload = function() {
            app.changeDiv();
        }
        window.onresize = function() {
            app.changeDiv();
        }
        this.init();
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
        init() {
            this.$http.get(api.user.getSetting).then(response => {
                this.setting = response.body.data;
                this.setting.siteLinks =  eval('(' + this.setting.siteLinks + ')');
            })
        },
        //触发关闭按钮
        handleClose() {
            this.avatarDialog = false;
        },
        update(form) {
            this.$refs[form].validate((valid) => {
                if (valid) {
                    this.setting.about = window.markdownContent.getHTML(); //给content赋值
                    this.setting.aboutMd = window.markdownContent.getMarkdown(); //给contentMd赋值
                    this.setting.siteLinks = JSON.stringify(this.setting.siteLinks);
                    console.log(this.setting);
                    this.$http.post(api.user.updateSetting, JSON.stringify(this.setting)).then(response => {
                        if (response.body.code == 200) {
                            this._notify(response.body.msg, 'success')
                        } else {
                            this._notify(response.body.msg, 'error')
                        }
                        window.location.reload();
                    })
                }
            })
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
