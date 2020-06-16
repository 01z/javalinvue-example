<template id="landing-page">
    <app-frame>
        <div class="mt-2"></div>
        <b-jumbotron header="BootstrapVue" lead="Bootstrap v4 Components for Vue.js 2">
            <p>For more information visit website</p>
            <b-button size="sm" @click="toggle">
                {{ show ? 'Hide' : 'Show' }} Alert
            </b-button>
            <b-alert
                    v-model="show"
                    class="mt-3"
                    dismissible
                    @dismissed="dismissed"
            >
                Hello {{ name }}!
            </b-alert>
        </b-jumbotron>
    </app-frame>
</template>
<script>
    Vue.component("landing-page", {
        template: "#landing-page",
        data() {
            return {
                name: 'BootstrapVue',
                show: true
            }
        },
        watch: {
            show(newVal) {
                console.log('Alert is now ' + (newVal ? 'visible' : 'hidden'))
            }
        },
        methods: {
            toggle() {
                console.log('Toggle button clicked')
                this.show = !this.show
            },
            dismissed() {
                console.log('Alert dismissed')
            }
        }
    });
</script>
<style>
</style>
