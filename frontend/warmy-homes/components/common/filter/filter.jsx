import styles from "./filter.module.scss";
import { FaSearch } from "react-icons/fa";

export default function Filter() {


    const category_id = [{title:"House", id:1}, {title:"Apartment", id:2}, {title: "Villa", id:3}, {title: "Office", id:4}]
    const advert_type_id= [{title:"All", id:3}, {title:"Rent", id:1}, {title: "Sale", id:2}]

    return (
        <div className={styles.container}>

            <form action='/properties' method='get' autoComplete="off">

                <div className={`${styles.field} ${styles.searchBox}`}>
                    <h5>Find your home</h5>
                    <input type='text' name='q' autoComplete="off" placeholder="What are you looking for?" />
                    <div className={styles.searchIcon}>
                        <FaSearch />
                    </div>
                </div>

                <div className={`${styles.field} ${styles.propertyStatus}`}>
                    <h5>Property Status</h5>
                    {advert_type_id.map((category) => (
                        <div key={category.id}>
                            <input className={styles.radio} type='radio' name='advert_type_id' id={category.title} value={category.id} />
                            <label className={styles.radioLabel} htmlFor={category.title}>{category.title}</label>
                        </div>
                    ))}
                </div>

                <div className={`${styles.field} ${styles.propertyType}`}>
                    <h5>Property Status</h5>
                    {category_id.map((category) => (
                        <div key={category.id}>
                            <input className={styles.radio} type='radio' name='category_id' id={category.title} value={category.id} />
                            <label className={styles.radioLabel} htmlFor={category.title}>{category.title}</label>
                        </div>
                    ))}
                </div>

                <div className={`${styles.field} ${styles.priceRange}`}>
                    <h5>Price Range</h5>
                    <div>
                        <input type='text' name='min' autoComplete="off" placeholder="Min" />
                        <input type='text' name='max' autoComplete="off" placeholder="Max" />
                    </div>
                </div>

                <div className={`${styles.field} ${styles.location}`}>
                    <h5>Location</h5>
                    <input type='text' name='location' autoComplete="off" placeholder="City" />
                </div>

                <button className={styles.submitButton} type='submit'><FaSearch /> Search</button>
            </form>
        </div>
    )
}
