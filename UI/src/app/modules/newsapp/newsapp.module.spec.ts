import { NewsappModule } from './newsapp.module';

describe('NewsappModule', () => {
  let newsappModule: NewsappModule;

  beforeEach(() => {
    newsappModule = new NewsappModule();
  });

  it('should create an instance', () => {
    expect(newsappModule).toBeTruthy();
  });
});
