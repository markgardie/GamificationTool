document.addEventListener('DOMContentLoaded', function() {
    const addButton = document.querySelector('.add-task-btn');
    const form = document.querySelector('.add-task-form');

    if (addButton && form) {
        addButton.addEventListener('click', function() {
            form.classList.toggle('visible');
        });
    }
});