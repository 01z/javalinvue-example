<template id="landing-page">
    <app-frame>
        <div class="mt-2"></div>
        <b-jumbotron header="BootstrapVue">
            <template v-slot:lead>
                <b-icon class="mt-3" icon="play-fill" variant="dark"></b-icon>
                Bootstrap 4.x frontend, served by Javalin & powered by Kotlin.
            </template>
            <b-button size="sm" @click="toggle">
                {{ show ? 'Hide' : 'Show' }} State-Section
            </b-button>
        </b-jumbotron>
        <div v-show="show" >
            <b-alert
                    v-model="show"
                    class="mt-3"
                    dismissible
                    @dismissed="dismissed"
            >
                Add some {{ name }} sesson-stored messages !
            </b-alert>
            <state-submit></state-submit>
            <state-display ></state-display>
        </div>
    </app-frame>
</template>
<script type="module">
    import {sayHi, xxx} from './test.js';
    sayHi('landing-page')
    console.log(xxx)

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
