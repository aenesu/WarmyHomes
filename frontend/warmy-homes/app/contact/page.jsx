
import ContactForm from "@/components/common/contact-form/contact-form"
import styles from "./contact.module.scss"

export default function ContactPage() {
  return (
    <div className={styles.mainContainer}>

      <div className={styles.banner}>
        <p>CONTACT US</p>
      </div>

      <div className={styles.mapEmbed}>
        <iframe
          className={styles.map}
          src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d97657.58994139378!2d32.849475!3d39.9244257!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2str&maptype=roadmap&zoom=12&disableDefaultUI=true&zoomControl=false&scaleControl=false&streetViewControl=false&fullscreenControl=false"
          allowFullScreen=""
          loading="lazy"
          referrerPolicy="no-referrer-when-downgrade"
        ></iframe>
      </div>

      <div className={styles.formGroup}>
        <div className={styles.formContainer}>
          <span>Have a question? Get in touch!</span>
          <ContactForm />
        </div>
        <div className={styles.text}></div>
      </div>
      <div className={styles.bottomText}>

      </div>


    </div>
  )
}
