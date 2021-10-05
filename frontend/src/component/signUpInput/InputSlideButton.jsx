import React, { PureComponent } from 'react';
import { FaArrowLeft, FaArrowRight } from "react-icons/fa";
import style from "../../css/signUp/inputSignUpContent/inputSignUpContent.module.css";

class InputSlideButton extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style.buttons}>
              <div className={`swiper-button-prev ${style["prev-button"]}`}>
                <FaArrowLeft />
              </div>
              <div className={`swiper-button-next ${style["next-button"]}`}>
                <FaArrowRight />
              </div>
            </div>
          </React.Fragment>
        );
    }
}

export default InputSlideButton;