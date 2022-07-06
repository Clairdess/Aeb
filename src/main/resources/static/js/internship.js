Vue.component('item-title', {
    props: ['message'],
    template: '<div class="center p-bottom-space"><p>Стажировка на направление «{{message.track}}»</p></div>'
});

Vue.component('item-date', {
    props: ['message'],
    template: '<div><p>Срок стажировки: {{message.start_date}} - {{message.end_date}}</p></div>'
});

Vue.component('item-due-date', {
    props: ['message'],
    template: '<div><p>Прием заявок на стажировку до <span class="main-intro-aeb-title-gradient">{{message.due_date}}</span></p></div>'
});

Vue.component('item-apply', {
    props: ['message'],
    template: '<div class="apply-button">Подать заявку</div>'
});

Vue.component('item-content', {
    props: ['message'],
    template: '<div class="intern-item-content"><item-title :message="message" /><item-date :message="message" /><item-due-date :message="message" /> <item-apply :message="message" /></div>'
});

Vue.component('internships-item', {
    props: ['message'],
    template: '<div class="intern-item"><item-content :message="message" /></div>'
});

Vue.component('internships-list', {
    props: ['messages'],
    template: '<div>' +
                '<internships-item v-for="message in messages" :key="message.id" :message="message" />' +
                '</div>'

});

var app = new Vue({
    el: "#internships",
    template: '<internships-list class="internships-list" :messages="messages" />',
    data: {
        messages: [
            { id:'1', track:'Аналитика', start_date: '01.09.2022', end_date: '30.09.2022', due_date: '14.08.2022', desc: ''},
            { id:'2', track:'Мобильная разработка', start_date: '01.08.2022', end_date: '15.08.2022', due_date: '01.07.2022', desc: '' },
            { id:'3', track:'UX/UI дизайн', start_date: '15.09.2022', end_date: '10.10.2022', due_date: '03.09.2022', desc: '' },
        ]
    }
});
