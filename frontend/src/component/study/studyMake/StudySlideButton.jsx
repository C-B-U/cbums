import React, { PureComponent } from "react";

class StudySlideButton extends PureComponent {
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
        <div className="studymake_buttons">
          <div
            id="prevButton"
            onClick={this.buttonPrevClick}
            className="swiper-button-prev"
          >
            <h4>이전</h4>
          </div>
          <div
            id="nextButton"
            onClick={this.buttonNextClick}
            className={
              this.state.indexNumber === 2
                ? "disabled swiper-button-next"
                : "swiper-button-next"
            }
          >
            <h4>다음</h4>
          </div>
        </div>
        <div className="main__slide-submit">
          <button
            className={this.state.indexNumber === 2 ? "" : "disabled"}
            id="submitButton"
            type="button"
          >
            <h4>개설</h4>
          </button>
        </div>
      </React.Fragment>
    );
  }
}

export default StudySlideButton;
