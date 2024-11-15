document.addEventListener('DOMContentLoaded', function() {
    // Обробка кнопки "Додати правило"
    const addRuleBtn = document.querySelector('.add-rule-btn');
    if (addRuleBtn) {
        addRuleBtn.addEventListener('click', function() {
            const form = document.querySelector('.add-rule-form');
            form.style.display = form.style.display === 'none' ? 'block' : 'none';
        });
    }

    // Ініціалізація селектів
    const motivationTypeSelect = document.getElementById('motivationType');
    const coreDriveSelect = document.getElementById('coreDrive');
    const gameElementSelect = document.getElementById('gameElement');

    if (!motivationTypeSelect || !coreDriveSelect || !gameElementSelect) return;

    // Функція оновлення драйверів
    async function updateCoreDrives(selectedType) {
        if (selectedType) {
            coreDriveSelect.disabled = false;
            try {
                const response = await fetch(`/game-rules/core-drives?motivationType=${selectedType}`);
                const drives = await response.json();

                coreDriveSelect.innerHTML = '<option value="">Виберіть драйвер</option>';
                drives.forEach(drive => {
                    const option = new Option(drive.ukName, drive.name);
                    if (window.currentRule?.coreDrive && drive.name === window.currentRule.coreDrive.name) {
                        option.selected = true;
                    }
                    coreDriveSelect.add(option);
                });

                // Якщо це форма редагування і є вибраний драйвер, оновлюємо ігрові елементи
                if (window.currentRule?.coreDrive) {
                    await updateGameElements(window.currentRule.coreDrive.name);
                }
            } catch (error) {
                console.error('Помилка при завантаженні драйверів:', error);
            }
        } else {
            coreDriveSelect.disabled = true;
            coreDriveSelect.innerHTML = '<option value="">Спочатку виберіть тип мотивації</option>';
            gameElementSelect.disabled = true;
            gameElementSelect.innerHTML = '<option value="">Спочатку виберіть драйвер</option>';
        }
    }

    // Функція оновлення ігрових елементів
    async function updateGameElements(selectedDrive) {
        if (selectedDrive) {
            gameElementSelect.disabled = false;
            try {
                const response = await fetch(`/game-rules/game-elements?coreDrive=${selectedDrive}`);
                const elements = await response.json();

                gameElementSelect.innerHTML = '<option value="">Виберіть ігровий елемент</option>';
                elements.forEach(element => {
                    const option = new Option(element.ukName, element.name);
                    if (window.currentRule?.gameElement && element.name === window.currentRule.gameElement.name) {
                        option.selected = true;
                    }
                    gameElementSelect.add(option);
                });
            } catch (error) {
                console.error('Помилка при завантаженні ігрових елементів:', error);
            }
        } else {
            gameElementSelect.disabled = true;
            gameElementSelect.innerHTML = '<option value="">Спочатку виберіть драйвер</option>';
        }
    }

    // Обробники подій для селектів
    motivationTypeSelect.addEventListener('change', function() {
        updateCoreDrives(this.value);
    });

    coreDriveSelect.addEventListener('change', function() {
        updateGameElements(this.value);
    });

    // Якщо це форма редагування, ініціалізуємо початкові значення
    if (window.currentRule) {
        updateCoreDrives(window.currentRule.motivationType.name);
    }
});