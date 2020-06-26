// <b-card-text v-html="randomText(10000)"></b-card-text>
export function randomText(len) {
    let text = " "
    let chars = "abcdefghijklmnopqrstuvwxyz"

    for( let i=0; i < len * Math.random()*10; i++ ) {
        text += chars.charAt(Math.floor(Math.random() * chars.length))
    }

    return text
};

export function makeToast(vue, variant = null) {
    vue.$bvToast.toast('Toast body content', {
        title: `Variant ${variant || 'default'}`,
        variant: variant,
        solid: true
    })
};

export function convertFileSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    if (bytes == 0) return '0 Byte';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
};

export function getIsoDate(date) {
    return date.toISOString().substring(0, 10)
}

export function addDays(isoDate, days) {
    const date = new Date(Date.parse(isoDate))
    const copy = new Date(Number(date))
    copy.setDate(date.getDate() + days)
    return getIsoDate(copy)
}

export function randomInt(from, to) {
    return Math.round(Math.random() * to) + from;
}

export function formatItemTimestamp(item, full) {
    return formatTimestamp(item.updated === undefined ? item.created : item.updated, full);
}

function formatTimestamp(ts, full) {
    const options = full ? { weekday: 'short', year: 'numeric', month: 'long', day: 'numeric',
        hour: '2-digit', minute:'2-digit', second:'2-digit'} :
        { weekday: 'short' , hour: '2-digit', minute:'2-digit', second:'2-digit'}
    return new Date(Date.parse(ts)).toLocaleString("de", options);
};

