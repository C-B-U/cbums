import React, { PureComponent } from "react";
class SlideBox extends PureComponent {
  render() {
    const studyName = this.props;
    const Color = this.props;
    const studyStyle = {
      backgroundColor: { Color },
    };
    return (
      <React.Fragment>
        <div class="main__slide-box" style={studyStyle}></div>
        <h3>{studyName}　</h3>
      </React.Fragment>
    );
  }
}

export default SlideBox;
