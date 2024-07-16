import Image from 'next/image';
import styles from './properity.module.scss';
import Gallery from '@/components/common/gallery/gallery';

export default function RandomProp() {

  const advert = {
    id: '',
    title: 'Bogaz Manzaralı Satılık Yalı',
    desc: `<b>Göksu ile Küçüksu dereleri arasında</b> kalan çayırlık alan, Osmanlı döneminde padişahların Boğaziçi'ndeki hasbahçelerinden, zamanla da en gözde mesire yerlerinden biri olarak tanınmaktadır. 
            17. yüzyılda ünlü seyyah Evliyâ Çelebi, “bir âb-ı hayât nehirdir” diye bahsettiği Göksu'yu, üzerinde kayıklarla dolaşılan; etrafı gül bahçeleri, küçük köşkler ve hazineye ait değirmenlerle çevrili sakin bir yer olarak tasvir etmiştir. Sultan IV. Murad (1623-1640), Kandilli'ye kadar sık selvi ağaçlarıyla kaplı Küçüksu ve çevresini düzenlettirerek buraya “Gümüş Selvi” adını vermiştir.
            Hasbahçe içindeki ilk yapılaşma Sultan I. Mahmud (1730-1754) döneminde başlamıştır. Göksu'da sık sık avlanan ve atış talimleri yapan Sultan için Sadrazam Divitdâr Mehmed Emin Paşa, 1751-1752 yıllarında ahşap bir köşk yaptırmıştır. Deniz kıyısındaki bu iki katlı yapı, Sultan III. Selim (1789-1807) döneminde geniş çaplı bir onarımdan geçmiş ve Sultan'ın isteği üzerine çok sevdiği annesi Mihrişah Valide Sultan adına 1806'da bir de çeşme eklenmiştir. Sultan II. Mahmud (1808-1839) döneminde de kullanılmaya devam eden eski köşk, Sultan Abdülmecid (1839-1861) tarafından yıktırılmış ve yerine 1856-1857 yıllarında yeni Küçüksu Kasrı yaptırılmıştır. Sultan Abdülaziz (1861-1876) döneminde, kasrın cephe süslemeleri elden geçirilerek zenginleştirilmiştir.`,
    slug: 'bosphorus-mansion',
    price: 40000000,
    status: '',
    built_in: 1856,
    is_active: '',
    view_count: 562,
    location: '',
    advert_type_id: 'For sale',
    country_id: 90,
    city_id: 34,
    district_id: 'Beykoz',
    user_id: '',
    category_id: '',
    create_at: '2 Days ago',
    update_at: '',
    details: {
      size: 860,
      bathrooms: 4,
      bedrooms: 12,
      garage: 0,
      furniture: true,
      maintainance_fee: 4000,
      terrace: true
    }
  }

  return (
    <div className={styles.mainContainer}>

      <div className={styles.banner}>
        <div className={styles.bannerInfo}>
          <div className={styles.title}>{advert.title}</div>
          <div className={styles.bannerIconContainer}>
            <div className={styles.bannerIcon}><img src="/assets/vectors/location-pin.svg" alt="Location" /> {advert.city_id === 34 ? "Istanbul" : "N/A"}{advert.district_id ? `, ${advert.district_id}` : ''} </div>
            <div className={styles.bannerIcon}><img src="/assets/vectors/label.svg" alt="Rent or Sale" /> {advert.advert_type_id} </div>
            <div className={styles.bannerIcon}><img src="/assets/vectors/clock.svg" alt="Posted" /> {advert.create_at} </div>
            <div className={styles.bannerIcon}><img src="/assets/vectors/view-count.svg" alt="View Count" /> {advert.view_count} </div>
          </div>
        </div>
        <div className={styles.bannerPrice}>
          $ {advert.price.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
        </div>
      </div>

      <div className={styles.content}>

        {/* Photos */}
        <div className={styles.left}>
          <div className={`${styles.container} ${styles.photoMainContainer}`} >
            <Gallery />

            {/*
            
            <div className={`${styles.photoContainer}`} >
              <Image
                src={"/assets/images/mock/mockdata3.jpeg"}
                fill alt='Image 1' />
            </div>
            <div className={styles.prewContainer}>
              <div className={styles.prewPhoto}> <Image src={"/assets/images/mock/mockdata2.jpg"} fill alt='Image 1' /> </div>
              <div className={styles.prewPhoto}> <Image src={"/assets/images/mock/mockdata1.jpeg"} fill alt='Image 1' /> </div>
              <div className={styles.prewPhoto}> <Image src={"/assets/images/mock/mockdata2.webp"} fill alt='Image 1' /> </div>
              <div className={styles.prewPhoto}> <Image src={"/assets/images/mock/mockdata3.jpeg"} fill alt='Image 1' /> </div>
              <div className={styles.prewPhoto}> <Image src={"/assets/images/mock/mockdata4.webp"} fill alt='Image 1' /> </div>
            </div> 
            
            */}
          </div>

          {/* ___________________________________________________________________________________*/}
          {/* Description */}
          <div className={styles.container}>
            <h3>Description</h3>
            <p dangerouslySetInnerHTML={{ __html: advert.desc }} />
          </div>

          {/* ___________________________________________________________________________________*/}
          {/* Details */}
          <div className={`${styles.container} ${styles.detailsContainer}`}>
            <h3>Details</h3>
            <div className={styles.details}>
              <div className={styles.detailBox}>
                <div className={styles.subDiv}> <h4>Size</h4>               <p>{advert.details.size} m&sup2;              </p></div>
                <div className={styles.subDiv}> <h4>Bathrooms</h4>          <p>{advert.details.bathrooms}                 </p></div>
                <div className={styles.subDiv}> <h4>Bedrooms</h4>           <p>{advert.details.bedrooms}                  </p></div>
                <div className={styles.subDiv}> <h4>Garage</h4>             <p>{advert.details.garage}                    </p></div>
              </div>
              <div className={styles.detailBox}>
                <div className={styles.subDiv}> <h4>Year of Build</h4>      <p>{advert.built_in}                          </p></div>
                <div className={styles.subDiv}> <h4>Furniture</h4>          <p>{advert.details.furniture ? "Yes" : "No"}  </p></div>
                <div className={styles.subDiv}> <h4>Maintainance Fee</h4>   <p>${advert.details.maintainance_fee}         </p></div>
                <div className={styles.subDiv}> <h4>Terrace</h4>            <p>{advert.details.terrace ? "Yes" : "No"}    </p></div>
              </div>
            </div>
          </div>

          {/* ___________________________________________________________________________________*/}
          {/* Location Info */}
          <div className={styles.container}>
            <h3>Location</h3>
            <div className={styles.locationDetails}>
              <p>Country: {advert.country_id === 90 ? "Türkiye" : "N/A"}</p>
              <p>City: Istanbul</p>
              <p>District: Beykoz</p>
            </div>
            <div className={styles.map}>
              <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d51639.455346759016!2d29.060852718756536!3d41.08059772353231!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14cac9f43f832e2f%3A0xde332daf29a35547!2zS8O8w6fDvGtzdSBQYXZpbGlvbg!5e0!3m2!1sen!2str!4v1719962638331!5m2!1sen!2str"
                height="100%"
                width="100%"
              ></iframe>
            </div>
          </div>

        </div>

        {/* ___________________________________________________________________________________*/}
        {/* Tour Scheduling */}
        <div className={styles.right}>
          <div className={`${styles.container} ${styles.tourContainer}`}>
            <div>
              <h3>Schedule a tour</h3>
              <p>Choose your preferred day</p>
            </div>
            <form action="">
              <input className={styles.tourDate} type="date" name="tour_date" />
              <input className={styles.tourTime} type="time" name="tour_time" min="09:00" step="1800" />
              <button>Submit a tour request</button>
            </form>
          </div>
        </div>
      </div>

    </div>
  )
}