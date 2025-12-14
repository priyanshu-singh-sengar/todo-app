// ---------- THEME ----------
function applyTheme(theme) {
    document.body.dataset.theme = theme;
    localStorage.setItem("theme", theme);
}

function toggleTheme() {
    const current = localStorage.getItem("theme") || "light";
    applyTheme(current === "dark" ? "light" : "dark");
}

(function () {
    const saved = localStorage.getItem("theme") || "light";
    document.body.dataset.theme = saved;
})();

// ---------- TYPE ANYWHERE ----------
document.addEventListener("keydown", (e) => {
    const active = document.activeElement;
    if (active.tagName === "INPUT" || active.tagName === "TEXTAREA") return;

    if (e.key.length === 1) {
        const input = document.querySelector(".add-form input");
        if (input) {
            input.focus();
            input.value += e.key;
        }
    }
});

// ---------- EDIT ----------
function enableEdit(element) {
    const id = element.dataset.id;
    document.getElementById("text-" + id).style.display = "none";
    document.getElementById("edit-" + id).style.display = "block";
}

// ---------- TIMERS ----------
const timers = {};
let totalSeconds = Number(localStorage.getItem("totalTime") || 0);

function formatTime(sec) {
    const h = String(Math.floor(sec / 3600)).padStart(2, "0");
    const m = String(Math.floor((sec % 3600) / 60)).padStart(2, "0");
    const s = String(sec % 60).padStart(2, "0");
    return `${h}:${m}:${s}`;
}

function updateTotal() {
    document.getElementById("totalTime").textContent = formatTime(totalSeconds);
    localStorage.setItem("totalTime", totalSeconds);
}
updateTotal();

function clearActive(li) {
    li.querySelectorAll(".timer-btn").forEach(b => b.classList.remove("active"));
}

function startTimer(btn) {
    const li = btn.closest("li");

    if (li.dataset.completed === "true") {
        li.querySelector(".toggle-form").submit();
        return;
    }

    const span = li.querySelector(".task-time");
    if (timers[li]) return;

    clearActive(li);
    btn.classList.add("active");

    timers[li] = setInterval(() => {
        let t = Number(span.dataset.time);
        t++;
        span.dataset.time = t;
        span.textContent = formatTime(t);
    }, 1000);
}

function pauseTimer(btn) {
    const li = btn.closest("li");
    clearInterval(timers[li]);
    delete timers[li];

    clearActive(li);
    btn.classList.add("active");
}

function resetTimer(btn) {
    const li = btn.closest("li");
    pauseTimer(btn);

    const span = li.querySelector(".task-time");
    span.dataset.time = 0;
    span.textContent = "00:00:00";

    clearActive(li);
    btn.classList.add("active");
}

function finalizeTaskTime(form) {
    const li = form.closest("li");
    const span = li.querySelector(".task-time");

    totalSeconds += Number(span.dataset.time);
    updateTotal();

    clearInterval(timers[li]);
    delete timers[li];
}
