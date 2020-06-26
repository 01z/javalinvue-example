<template id="state-submit">
    <div class="container">
        <div class="row">
            <div class="col-10">
                <b-form-input v-model="itemInput" type="text"
                              :placeholder="`Add something to state ... (like ${randomText(10)})`"
                              v-on:keyup.enter="addItem"
                >
                </b-form-input>
            </div>
            <div class="col">
                <b-button  @click="addItem">Add</b-button>
                <b-button v-show="false" @click="clearItems" variant="danger" >Clear</b-button>
            </div>
        </div>
    </div>
</template>

<script type="module">

    // import mixins module to use external function in :attribute="fx(xx)"
    import mixins from "/mixins.js";

    Vue.component("state-submit", {
        template: "#state-submit",
        mixins: [mixins],
        data() {
            return {
                itemInput: ""
            };
        },
        methods: {
            addItem() {
                this.$store.dispatch("addItem", { name: this.itemInput });
                this.itemInput = '';
            },
            clearItems() {
                // destroyState();
                // initializeState();
                //this.$store.replaceState(initialState);
            }
        }
    });
</script>