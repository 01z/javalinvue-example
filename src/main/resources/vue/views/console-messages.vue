<template id="console-messages">
    <app-frame>
        <div>
            <h3>Console-Messages for {{ auth.user }}</h3>
            <pre>
                <span v-for="message in messages">{{message.type}}: {{message.data}}
                </span>
            </pre>
        </div>
    </app-frame>
</template>
<script>
    const eventSource = new EventSource("/sse");
    eventSource.addEventListener("connected", (msg) => console.log(msg));

    Vue.component("console-messages", {
        template: "#console-messages",
        data: () => ({
            messages: []
        }),
        created() {
            eventSource.addEventListener("update", (msg) => {
                 this.messages.unshift(msg)
            });
        },
        computed: {
            auth() {
                return this.$store.getters.auth
            },
        },
        beforeRouteLeave (to, from, next) {
            const answer = window.confirm('Have you seen enough?')
            if (answer) {
                next()
            } else {
                next(false)
            }
        }
    });
</script>
