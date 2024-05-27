import styles from "./lets-find-the-right-option.module.scss";

export default function LetsFindTheRightOption() {
    return (
        <div className={styles.container}>

            <div className={styles.textContainer}>
                <div className={styles.title}>Let’s Find The Right <br /> Selling Option For You</div>
                <div className={styles.sub}>
                    <div className={styles.subIcon}><img src="/assets/vectors/pricing-tag.svg" alt="pricing-tag" width={50}/></div>
                    <div className={styles.subText}>
                        <div className={styles.subTextTitle}>Tech-Driven Marketing</div>
                        <div className={styles.subTextText}>Real estate is embracing technology with virtual tours, 3D models, and blockchain transactions.</div>
                    </div>
                </div>
                <div className={styles.sub}>
                    <div className={styles.subIcon}><img src="/assets/vectors/sustainibility.svg" alt="sustainibility" width={50}/></div>
                    <div className={styles.subText}>
                        <div className={styles.subTextTitle}>Sustainability Matters</div>
                        <div className={styles.subTextText}>Green building practices and eco-friendly features are gaining popularity for environmentally conscious buyers.</div>
                    </div>
                </div>
                <div className={styles.sub}>
                    <div className={styles.subIcon}><img src="/assets/vectors/vault.svg" alt="safe" width={45}/></div>
                    <div className={styles.subText}>
                        <div className={styles.subTextTitle}>Remote Work Impact</div>
                        <div className={styles.subTextText}>Changing work patterns are reshaping housing preferences, favoring suburban and urban mixed-use developments.</div>
                    </div>
                </div>
            </div>

            <div className={styles.imageContainer}><img src="/assets/images/happy-couple-dancing-kitchen 1.jpg" alt="" /></div>

        </div>
    )
}
