<template id="console-messages">
    <app-frame>
        <div>
            <h3>Console-Messages for {{$javalin.state.currentUser}}</h3>
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
        }
    });
</script>
