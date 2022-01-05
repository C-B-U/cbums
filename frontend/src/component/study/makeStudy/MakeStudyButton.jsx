import React, { PureComponent } from "react";
import style from "../../../css/study/study_make/study_make.module.css";

class MakeStudyButton extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      isDisabled: true,
      isNext: true,
      indexNumber: 0,
    };
    this.buttonNextClick = this.buttonNextClick.bind(this);
    this.buttonPrevClick = this.buttonPrevClick.bind(this);
  }

  buttonNextClick = () => {
    this.setState({
      indexNumber: this.state.indexNumber + 1,
    });
  };
  buttonPrevClick = () => {
    this.setState({
      indexNumber: this.state.indexNumber - 1,
    });
  };

  render() {
    return (
      <React.Fragment>
        <div className={style.studymake_buttons}>
          <div
            id={style["prevButton"]}
            onClick={this.buttonPrevClick}
            className="swiper-button-prev"
          >
            <h4>이전</h4>
          </div>
          <div
            id={style["nextButton"]}
            onClick={this.buttonNextClick}
            className={
              this.state.indexNumber === 2
                ? `${style.disabled} swiper-button-next`
                : "swiper-button-next"
            }
          >
            <h4>다음</h4>
          </div>
        </div>
        <div className={style["main__slide-submit"]}>
          <input
            className={this.state.indexNumber === 2 ? "" : style.disabled}
            id={style["submitButton"]}
            type="submit"
            value="개설"
          />
        </div>
      </React.Fragment>
    );
  }
}

export default MakeStudyButton;
