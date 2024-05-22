document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;
    
    // Email sending logic goes here
    
    alert(`Thank you ${name}! Your message has been sent.`);
});

<main>
<section id="about">
    <h2>About Me</h2>
    <p>I am an ongoing MCA (Master of Computer Applications) graduate seeking an entry-level position in the field of computer science or software development.</p>
</section>

<section id="experience">
    <h2>Experience</h2>
    <div class="job">
        <h3>No professional experience yet</h3>
        <p>I am an ongoing MCA (Master of Computer Applications) graduate seeking an entry-level position in the field of computer science or software development.</p>
    </div>
</section>

<section id="education">
    <h2>Education</h2>
    <div class="education">
        <h3>Master of Computer Applications (MCA)</h3>
        <p class="text-muted">Rajagiri College Of Social Science | Ongoing</p>
        <p>Expected Graduation: May 2025</p>
    </div>
    <div class="education">
        <h3>Bachelor of Science in Physics</h3>
        <p class="text-muted">SreeKeralaVarama College| Graduated: May 2021</p>
    </div>
</section>

<section id="skills">
    <h2>Skills</h2>
    <div class="skills-container">
        <div class="skill">
            <i class="fab fa-java"></i>
            <span>Java</span>
        </div>
        <div class="skill">
            <i class="fab fa-python"></i>
            <span>Python</span>
        </div>
        <div class="skill">
            <i class="fab fa-html5"></i>
            <span>HTML/CSS</span>
        </div>
        <div class="skill">
            <i class="fab fa-js"></i>
            <span>JavaScript</span>
        </div>
        <div class="skill">
            <i class="fas fa-database"></i>
            <span>SQL</span>
        </div>
        <div class="skill">
            <i class="fab fa-git-alt"></i>
            <span>Git</span>
        </div>
    </div>
</section>

<section id="projects">
    <h2>Projects</h2>
    <div class="project">
        <div class="project-image">
            <img src="assets/project1.png" alt="Project 1">
        </div>
        <div class="project-details">
            <h3>Student Management System</h3>
            <p>Developed a Java-based desktop application for managing student records, courses, and grades.</p>
            <a href="https://github.com/your-username/student-management-system" target="_blank" class="project-link">GitHub Link</a>
        </div>
    </div>
    <div class="project">
        <div class="project-image">
            <img src="assets/project2.png" alt="Project 2">
        </div>
        <div class="project-details">
            <h3>Data Analysis Project</h3>
            <p>Performed data analysis and visualization on a physics dataset using Python libraries like NumPy, pandas, and Matplotlib.</p>
            <a href="https://github.com/your-username/data-analysis-project" target="_blank" class="project-link">GitHub Link</a>
        </div>
    </div>
</section>

<section id="contact">
    <h2>Contact Me</h2>
    <form id="contactForm" method="POST" action="send_email.php">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="message">Message</label>
            <textarea id="message" name="message" rows="4" required></textarea>
        </div>
        <button type="submit" class="btn">Send Message</button>
    </form>
</section>
</main>