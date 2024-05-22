
document.addEventListener('DOMContentLoaded', () => {
    const skills = document.querySelectorAll('.skill');
    const modal = document.getElementById('skillModal');
    const closeButton = document.querySelector('.close-button');
    const skillTitle = document.getElementById('skillTitle');
    const skillDescription = document.getElementById('skillDescription');
    const skillCertificate = document.getElementById('skillCertificate img');

    const skillDetails = {
        java: {
            title: 'Java',
            description: 'Developed proficiency in Java through coursework and projects such as the Student Management System.',
            certificate: 'assets/java-certificate.png'
        },
        python: {
            title: 'Python',
            description: 'Gained experience with Python through data analysis projects and coursework.',
            certificate: 'assets/python-certificate.png'
        },
        'html-css': {
            title: 'HTML/CSS',
            description: 'Built several websites and learned HTML/CSS through online courses.',
            certificate: 'assets/html-css-certificate.png'
        },
        javascript: {
            title: 'JavaScript',
            description: 'Enhanced front-end development skills using JavaScript in various projects.',
            certificate: 'assets/javascript-certificate.png'
        },
        sql: {
            title: 'SQL',
            description: 'Acquired SQL skills through database management courses and practical projects.',
            certificate: 'assets/sql-certificate.png'
        },
        git: {
            title: 'Git',
            description: 'Learned version control using Git in collaborative projects.',
            certificate: 'assets/git-certificate.png'
        },
        php: {
            title: 'PHP',
            description: 'Developed server-side scripting skills with PHP through web development projects.',
            certificate: 'assets/php-certificate.png'
        },
        laravel: {
            title: 'Laravel',
            description: 'Mastered Laravel framework through hands-on projects and coursework.',
            certificate: 'assets/laravel-certificate.png'
        },
        c: {
            title: 'C',
            description: 'Learned C programming language through coursework and assignments.',
            certificate: 'assets/c-certificate.png'
        },
        django: {
            title: 'Django',
            description: 'Built web applications using Django framework in various projects.',
            certificate: 'assets/django-certificate.png'
        }
    };

    skills.forEach(skill => {
        skill.addEventListener('click', () => {
            const skillKey = skill.getAttribute('data-skill');
            const details = skillDetails[skillKey];

            skillTitle.textContent = details.title;
            skillDescription.textContent = details.description;
            skillCertificate.src = details.certificate;

            modal.style.display = 'block';
        });
    });

    closeButton.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    window.addEventListener('click', event => {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    });
});
