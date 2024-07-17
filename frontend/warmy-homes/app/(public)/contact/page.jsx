
import ContactForm from "@/components/common/contact-form/contact-form"
import Banner from "@/components/common/banner/banner"
import styles from "./contact.module.scss"
import { akshar } from "../../layout"

export default function ContactPage() {

  const locations = [
    { icon: "/assets/vectors/paris.svg", title: "PARIS", address: "100 Esplanade du Général de Gaulle, 92400 Courbevoie, Paris, France", phone: "+33 1 49 06 40 00" },
    { icon: "/assets/vectors/london.svg", title: "LONDON", address: "1 Canada Square, Canary Wharf, London E14 5AB, United Kingdom", phone: "+44 20 7418 2000" },
    { icon: "/assets/vectors/istanbul.svg", title: "ISTANBUL", address: "Büyükdere Cd. No:185, Levent, 34394 Şişli/Istanbul, Türkiye", phone: "+90 212 317 5300" },
  ]


  return (
    <div className={styles.mainContainer}>
      <div className={styles.container}>

      <Banner title="CONTACT US" />

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
          <div className={styles.text}>
            <h1>We'd Love To Hear <br />From You.</h1>
            <p>We are here to answer any question you may have. As a partner of corporates, realton has more than 9,000 offices of all sizes and all potential of session.</p>
          </div>

        </div>

        <div className={styles.bottomContainer}>
          <div className={styles.bottomText}>
            <h2>Visit Our Office</h2>
            <p>Realton has more than 9,000 offices of all sizes and all potential of session.</p>
          </div>

          <div className={styles.locations}>
            {locations.map((location, index) => (
              <div key={index} className={styles.location}>
                <div className={styles.icon}><img src={location.icon} alt={location.title} /></div>
                <h2 className={styles.title}>{location.title}</h2>
                <p>{location.address}</p>
                <p>{location.phone}</p>
              </div>
            ))}
          </div>
        </div>

      </div>
    </div>
  )
}
