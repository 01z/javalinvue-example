<template id="state-display">
    <div>
        <b-table class="mt-3" striped hover :items="items"></b-table>
    </div>
</template>

<script>
    Vue.component("state-display", {
        template: "#state-display",
        computed: {
            ...Vuex.mapGetters([
                'items',
            ]),
        }
    });
</script>