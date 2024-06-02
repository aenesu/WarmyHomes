"use client"

import { useState } from "react";
import styles from "./contact-form.module.scss";
import { Akshar } from "next/font/google";

const akshar = Akshar({ subsets: ["latin"], weight: ["300", "400", "500", "600", "700"] });


export default function ContactForm() {


    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        message: "",
    })

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevFormData => ({
            ...prevFormData,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // API CONNECTION!!
        console.log('Submitted form data:', formData);
    };


    return (
        <div className={styles.container}>
            <form className={styles.formContainer} onSubmit={handleSubmit}>
                <div className={styles.formField}>
                    <label htmlFor="firstName">First Name</label>
                    <input className={akshar.className} type="text" id="firstName" name="firstName" onChange={handleChange} />
                </div>
                <div className={styles.formField}>
                    <label htmlFor="lastName">Last Name</label>
                    <input className={akshar.className} type="text" id="lastName" name="lastName" onChange={handleChange} />
                </div>
                <div className={styles.formField}>
                    <label htmlFor="email">Email Adress</label>
                    <input className={akshar.className} type="email" id="email" name="email" onChange={handleChange} />
                </div>
                <div className={styles.formField}>
                    <label htmlFor="message">Message</label>
                    <textarea className={akshar.className} id="message" name="message" onChange={handleChange} />
                </div>
                <button type="submit" className={`${styles.submitButton}  ${akshar.className}`}>SEND</button>
            </form>
        </div>
    )
}
