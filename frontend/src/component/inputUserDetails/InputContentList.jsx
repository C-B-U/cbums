import React, { PureComponent } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation } from "swiper";
import "swiper/components/navigation/navigation.scss";
import style from "../../css/signUp/inputSignUpContent/inputSignUpContent.module.css";
import "../../css/slide.css";
import "swiper/swiper.scss";
import InputContent from "./InputContent";
import InputSlideButton from "./InputSlideButton";
SwiperCore.use([Navigation]);

class InputContentList extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style.article__boxList}>
          <Swiper
            className={style["input-container"]}
            slidesPerView={1}
            slidesPerGroup={1}
            mousewheel={false}
            touchRatio={false}
            navigation={{
              nextEl: ".swiper-button-next",
              prevEl: ".swiper-button-prev",
            }}
          >
            <form action="#" method="POST">
              <ul className={style.boxList__infor}>
                <li className={style.boxList__li}>
                  <SwiperSlide className={style.box} key="input_1">
                    <InputContent
                      title="이름"
                      className={style["boxList__infor-input"]}
                      type="text"
                      placeholder="이름 입력"
                      name="name"
                    />
                  </SwiperSlide>
                </li>
                <SwiperSlide className={style.box} key="input_2">
                  <InputContent
                    title="학과"
                    className={style["boxList__infor-input"]}
                    type="text"
                    placeholder="학번 입력"
                    name="schoolNumber"
                  />
                </SwiperSlide>
                <SwiperSlide className={style.box} key="input_3">
                  <InputContent
                    title="전화번호"
                    className={style["boxList__infor-input"]}
                    type="text"
                    placeholder="전화번호 입력"
                    name="phoneNumber"
                  />
                </SwiperSlide>
                <SwiperSlide className={style.box} key="input_4">
                  <input
                    type="submit"
                    style={{ cursor: "pointer" }}
                    className={style["boxList__infor--submit"]}
                  />
                </SwiperSlide>
              </ul>
            </form>
          </Swiper>
          <InputSlideButton />
        </div>
      </React.Fragment>
    );
  }
}

export default InputContentList;
