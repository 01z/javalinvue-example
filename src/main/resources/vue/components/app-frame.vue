<template id="app-frame">
    <div class="app-frame">
        <!-- Image and text -->
        <app-header></app-header>
        <div class="container-fluid">
            <div class="row flex-xl-nowrap2">
                <div class="bd-content col-12">
                    <div class="mt-3"></div>
                    <slot></slot>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component("app-frame", {template: "#app-frame"});
</script>
<style>

</style>
